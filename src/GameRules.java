public class GameRules {
    public int numPlayers;
    public int endTime;
    public int lives;
    public int bulletDamage;
    public int numTeams;
    public Boolean randomTeachMatchmaking;
    public int bulletspeed;
    public int shootspeed;
    public boolean friendlyFire;
    public GameMode gameMode;
    public double deltaAngle;
    public double acc;
    public double increaseSpeed;
    public double maxSpeed;

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public Boolean getRandomTeachMatchmaking() {
        return randomTeachMatchmaking;
    }

    public void setRandomTeachMatchmaking(Boolean randomTeachMatchmaking) {
        this.randomTeachMatchmaking = randomTeachMatchmaking;
    }

    public int getBulletspeed() {
        return bulletspeed;
    }

    public void setBulletspeed(int bulletspeed) {
        this.bulletspeed = bulletspeed;
    }

    public int getShootspeed() {
        return shootspeed;
    }

    public void setShootspeed(int shootspeed) {
        this.shootspeed = shootspeed;
    }

    public boolean isFriendlyFire() {
        return friendlyFire;
    }

    public void setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public double getDeltaAngle() {
        return deltaAngle;
    }

    public void setDeltaAngle(double deltaAngle) {
        this.deltaAngle = deltaAngle;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public double getIncreaseSpeed() {
        return increaseSpeed;
    }

    public void setIncreaseSpeed(double increaseSpeed) {
        this.increaseSpeed = increaseSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public GameRules(int numPlayers, int endTime, int lives, int bulletDamage, int numTeams, Boolean randomTeachMatchmaking, int bulletspeed, int shootspeed, boolean friendlyFire, GameMode gameMode, double deltaAngle, double acc, double increaseSpeed, double maxSpeed) {
        this.numPlayers = numPlayers;
        this.endTime = endTime;
        this.lives = lives;
        this.bulletDamage = bulletDamage;
        this.numTeams = numTeams;
        this.randomTeachMatchmaking = randomTeachMatchmaking;
        this.bulletspeed = bulletspeed;
        this.shootspeed = shootspeed;
        this.friendlyFire = friendlyFire;
        this.gameMode = gameMode;
        this.deltaAngle = deltaAngle;
        this.acc = acc;
        this.increaseSpeed = increaseSpeed;
        this.maxSpeed = maxSpeed;
    }
}

