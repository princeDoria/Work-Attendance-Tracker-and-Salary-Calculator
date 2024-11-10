public class Data {
    protected String[] tableHeader = {"Family Name", "First Name", "Middle Name", "Suffix", "Role", "Skill A",
            "Skill B", "Birthday", "Contact Number"};
    protected String[] roleS = {"All", "Foreman", "Time Keeper", "Electrician", "Plumber", "Skilled Worker", "Laborer"};
    protected String[] roleNe = {"Foreman", "Time Keeper", "Electrician", "Plumber", "Skilled Worker", "Laborer"};
    protected String[] skillS = {"All", "Painter", "Steel Man", "Carpenter", "Mason", "Welder"};
    protected String[] skillNe = {"Painter", "Steel Man", "Carpenter", "Mason", "Welder"};
    protected String[] suffix = {"Sr", "Jr", "II", "III", "IV", "V","VI"};
    protected String[][] info = new String[99][99];     //employee table information
    protected int[][] baseS = new int[99][99];          //employee base salary, deduction, balance
    protected String[] names = {""};
    protected double initial = 0, finalSal = 0;
    protected int id = 0;
    protected int count = 0;
    protected String payment = "";
}
