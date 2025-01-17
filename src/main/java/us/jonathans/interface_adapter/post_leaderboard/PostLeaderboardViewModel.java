package us.jonathans.interface_adapter.post_leaderboard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostLeaderboardViewModel {
    private final String viewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PostLeaderboardViewModel(String viewName) {
        this.viewName = viewName;
    }

    public void firePropertyChanged(String viewName, Object newValue) {
        this.support.firePropertyChange(viewName, null, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
