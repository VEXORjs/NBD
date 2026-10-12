public abstract class User {
    private final Long id;
    private String username;
    private boolean isArchived;

    public User(String username, boolean isArchived) {
        this.username = username;
        this.isArchived = isArchived;
    }
    public Long getId() { return id; }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public boolean getIsArchived(){
        return isArchived;
    }

    public void setIsArchived(boolean isArchived){
        this.isArchived = isArchived;
    }

    public void unarchive(){
        isArchived = false;
    };

    public void archive(){
        isArchived = true;
    };
}