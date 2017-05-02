package state.concrete;

import main.Repliquant;
import state.Behavior;
import cz.cuni.amis.pogamut.base3d.worldview.object.ILocated;
import cz.cuni.amis.pogamut.ut2004.agent.navigation.IUT2004Navigation;
import cz.cuni.amis.pogamut.ut2004.communication.messages.gbinfomessages.Item;

public class Travel extends Behavior {
    
    IUT2004Navigation navigation;
    
    @Override
    public void performs(Repliquant unBot) {
        if (unBot.getNMNav().isAvailable())
            navigation = unBot.getNMNav();
        else
            navigation = unBot.getNavigation();
        Item obj = unBot.getItems().getNearestVisibleItem();
        if (navigation.isNavigating()) {
            if (obj != null) {
                ILocated last = navigation.getLastTarget();
                if (last != null) {
                    navigation.navigate(obj.getLocation());
                    navigation.setContinueTo(last);
                }
            }
        } else if (obj != null) {
            navigation.navigate(obj.getLocation());
        }
    }
}
