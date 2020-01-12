package com.github.franckyi.projettransversal.emergencymanager;

import com.github.franckyi.projettransversal.api.APIHandler;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

public class EmergencyManagerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("---");
        try {
            for (Feu feu : DAOFactory.getFeuDAO()) {
                if (feu.getIntensite() == 0) {
                    if (feu.getInterventions().size() == 0) {
                        DAOFactory.getFeuDAO().delete(feu);
                        continue;
                    }
                    for (Intervention intervention : feu.getInterventions()) {
                        DAOFactory.getCamionDAO().refresh(intervention.getCamion());
                        DAOFactory.getFeuDAO().refresh(intervention.getFeu());
                        this.retour(intervention);
                    }
                } else {
                    DAOFactory.getPointDAO().refresh(feu.getPoint());
                    if (feu.getInterventions().size() == 0) {
                        if (!newIntervention(feu)) {
                            break;
                        }
                    }
                    for (Intervention intervention : feu.getInterventions()) {
                        DAOFactory.getCamionDAO().refresh(intervention.getCamion());
                        Camion camion = intervention.getCamion();
                        Point point = feu.getPoint();
                        if (camion.posEquals(point)) {
                            feu.setIntensite(feu.getIntensite() - 1);
                            if (feu.getIntensite() > 0) {
                                System.out.println(String.format("Feu %d en cours d'extinction (%d) par le camion %d", feu.getIdFeu(), feu.getIntensite(), camion.getIdCamion()));
                            } else {
                                System.out.println(String.format("Feu %d éteint", feu.getIdFeu()));
                            }
                            DAOFactory.getFeuDAO().update(feu);
                        } else {
                            this.avancer(intervention);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean newIntervention(Feu feu) throws SQLException {
        List<Caserne> casernes = DAOFactory.getCaserneDAO().queryForAll();
        casernes.sort(Comparator.comparingDouble(o -> o.distance(feu.getPoint())));
        Camion camion = this.getCamion(casernes);
        if (camion != null) {
            List<Pos> directions = APIHandler.getDirections(camion.getCaserne(), feu.getPoint());
            System.out.println(String.format("Camion %d affecté au feu %d", camion.getIdCamion(), feu.getIdFeu()));
            Intervention intervention = new Intervention(feu, camion);
            DAOFactory.getInterventionDAO().create(intervention);
            List<Trajet> trajets = new ArrayList<>();
            int ordre = 1;
            trajets.add(new Trajet(camion, 0, camion.getCaserne().getLongitude(), camion.getCaserne().getLatitude()));
            for (Pos pos : directions) {
                trajets.add(new Trajet(camion, ordre++, pos));
            }
            trajets.add(new Trajet(camion, ordre, feu.getPoint().getLongitude(), feu.getPoint().getLatitude()));
            DAOFactory.getTrajetDAO().create(trajets);
            return true;
        } else {
            System.out.println(String.format("Aucun camion disponible pour feu %d", feu.getIdFeu()));
            return false;
        }
    }

    private Camion getCamion(List<Caserne> casernes) {
        for (Caserne caserne : casernes) {
            for (Camion camion : caserne.getCamions()) {
                if (camion.getInterventions().isEmpty()) {
                    return camion;
                }
            }
        }
        return null;
    }

    private void retour(Intervention intervention) throws SQLException {
        Camion camion = intervention.getCamion();
        DAOFactory.getCaserneDAO().refresh(camion.getCaserne());
        Trajet previous = DAOFactory.getTrajetDAO().queryPrevious(intervention);
        if (previous != null) {
            System.out.println(String.format("Camion %d sur le trajet du retour vers la caserne %d", camion.getIdCamion(), camion.getCaserne().getIdCaserne()));
            camion.setLongitude(previous.getLongitude());
            camion.setLatitude(previous.getLatitude());
            DAOFactory.getCamionDAO().update(camion);
            intervention.setTrajetActuel(intervention.getTrajetActuel() - 1);
            DAOFactory.getInterventionDAO().update(intervention);
        } else if (camion.posEquals(camion.getCaserne())) {
            System.out.println(String.format("Camion %d rentré à la caserne %d", camion.getIdCamion(), camion.getCaserne().getIdCaserne()));
            DAOFactory.getTrajetDAO().deleteForCamion(camion);
            DAOFactory.getInterventionDAO().delete(intervention);
        }
    }

    private void avancer(Intervention intervention) throws SQLException {
        Camion camion = intervention.getCamion();
        Trajet next = DAOFactory.getTrajetDAO().queryNext(intervention);
        if (next != null) {
            System.out.println(String.format("Camion %d avance vers le feu %d", camion.getIdCamion(), intervention.getFeu().getIdFeu()));
            camion.setLongitude(next.getLongitude());
            camion.setLatitude(next.getLatitude());
            DAOFactory.getCamionDAO().update(camion);
            intervention.setTrajetActuel(intervention.getTrajetActuel() + 1);
            DAOFactory.getInterventionDAO().update(intervention);
        }
    }

}
