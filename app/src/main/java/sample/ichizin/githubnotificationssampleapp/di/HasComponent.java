package sample.ichizin.githubnotificationssampleapp.di;

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 *
 * @author ichizin
 */
public interface HasComponent<C> {
    C getComponent();
}
