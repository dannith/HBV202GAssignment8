public class UserOrBookDoesNotExistException extends Exception{
    public UserOrBookDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}