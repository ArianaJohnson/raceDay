
//constructors that recieve the physical input from the user
class times {
    private String name;
    private int timeInMinutes;
    private int age;
    private String raceType;

    public times(String name, int timeInMinutes, int age, String raceType) {
        this.name = name;
        this.timeInMinutes = timeInMinutes;
        this.age = age;
        this.raceType = raceType;
    }

    public String getName() {
        return name;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public int getAge() {
        return age;
    }

    public String getRaceType() {
        return raceType;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Time (minutes): " + timeInMinutes + ", Age: " + age + ", Race Type: " + raceType;
    }
}