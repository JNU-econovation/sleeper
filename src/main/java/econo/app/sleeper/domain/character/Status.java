package econo.app.sleeper.domain.character;

public enum Status {
    SLEEP,NO_SLEEP;

    private Status(){

    }

    public Status opposite(String status){
        if ( status.equals("SLEEP")) {
            return Status.NO_SLEEP;
        }else{
            return Status.SLEEP;
        }
    }
}
