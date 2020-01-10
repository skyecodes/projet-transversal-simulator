package com.github.franckyi.projettransversal.emergencymanager;

import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.*;

import java.sql.SQLException;
import java.util.Arrays;
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
                        System.out.println(String.format("Feu %d éteint", feu.getIdFeu()));
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
                        System.out.println(String.format("Nouveau feu %d", feu.getIdFeu()));
                        newIntervention(feu);
                    }
                    for (Intervention intervention : feu.getInterventions()) {
                        DAOFactory.getCamionDAO().refresh(intervention.getCamion());
                        Camion camion = intervention.getCamion();
                        Point point = feu.getPoint();
                        if (camion.posEquals(point)) {
                            feu.setIntensite(feu.getIntensite() - 1);
                            System.out.println(String.format("Feu %d en cours d'extinction (%d) par le camion %d", feu.getIdFeu(), feu.getIntensite(), camion.getIdCamion()));
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

    private void newIntervention(Feu feu) throws SQLException {
        List<Caserne> casernes = DAOFactory.getCaserneDAO().queryForAll();
        casernes.sort(Comparator.comparingDouble(o -> o.distance(feu.getPoint())));
        Camion camion = null;
        for (Caserne caserne : casernes) {
            for (Camion c : caserne.getCamions()) {
                if (c.posEquals(caserne)) {
                    camion = c;
                    break;
                }
            }
        }
        if (camion != null) {
            System.out.println(String.format("Camion %d affecté au feu %d", camion.getIdCamion(), feu.getIdFeu()));
            Intervention intervention = new Intervention(feu, camion);
            DAOFactory.getInterventionDAO().create(intervention);
            Trajet trajet0 = new Trajet(camion, 0, camion.getCaserne().getLongitude(), camion.getCaserne().getLatitude());
            Trajet trajet1 = new Trajet(camion, 1, feu.getPoint().getLongitude(), feu.getPoint().getLatitude());
            DAOFactory.getTrajetDAO().create(Arrays.asList(trajet0, trajet1));
        }
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
