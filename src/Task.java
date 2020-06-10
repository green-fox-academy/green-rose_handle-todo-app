public class Task {
    public String task;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Task(String task){
        this.task = task;
        this.status = false;
    }

    public Task(String[] jsonTask){

        this.task = jsonTask[0];
        this.status = jsonTask.length>1&&jsonTask[1].compareTo("done")==0;
    }

    @Override
    public String toString() {
        return "["+(this.isStatus()?"x":" ")+"] "+this.task;
    }

    public String toFile(){
       return this.task + (this.isStatus()?":done":": ");
    }
}
