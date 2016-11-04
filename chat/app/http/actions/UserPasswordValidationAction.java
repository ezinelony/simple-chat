package http.actions;


public class UserPasswordValidationAction extends UserValidationAction {
    protected boolean emailIsValid(String email){
        return true;
    }
}
