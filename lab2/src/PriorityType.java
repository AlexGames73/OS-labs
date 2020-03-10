public enum PriorityType {
    Low(40),
    Middle(60),
    High(100),
    Realtime(200);

    private int time;

    PriorityType(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
