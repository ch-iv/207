package us.jonathans.InterfaceAdapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetLeaderboardViewModel {
    private final String viewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GetLeaderboardViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void firePropertyChanged(String viewName, Object newValue) {
        this.support.firePropertyChange(viewName, null, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
