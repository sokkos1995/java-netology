class SmartRobot extends Robot {
    private int stepsCount = 0;    
    
    public int getStepsCount() {
        return stepsCount;
    }

    @Override
    public void moveRight() {
        super.moveRight();
        stepsCount++;
    }    

    @Override
    public void moveLeft() {
        super.moveLeft();
        stepsCount++;        
    }

    @Override
    public void moveUp() {
        super.moveUp();
        stepsCount++;        
    }

    @Override
    public void moveDown() {
        super.moveDown();
        stepsCount++;        
    }    
}