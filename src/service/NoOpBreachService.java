package service;

public class NoOpBreachService implements BreachService {
    public boolean isBreached(String prefix) { return false; }
}
