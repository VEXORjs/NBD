public abstract class User {
    private String username;
    private boolean IsArchived;

    public User(String username, boolean IsArchived) {
        this.username = username;
        this.IsArchived = IsArchived;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public boolean getIsArchived(){
        return IsArchived;
    }
    public void setIsArchived(boolean IsArchived){
        this.IsArchived = IsArchived;
    }
    public void unarchive(){
        IsArchived = false;
    };
    public void archive(){
        IsArchived = true;
    };
}