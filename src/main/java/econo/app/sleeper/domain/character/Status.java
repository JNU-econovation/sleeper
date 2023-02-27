package econo.app.sleeper.domain.character;

public enum Status {
    SLEEP,NO_SLEEP;

    private Status(){

    }

    public Status opposite(){
        if ( this == Status.SLEEP) {
            return Status.NO_SLEEP;
        }else{
            return Status.SLEEP;
        }
    }
}
