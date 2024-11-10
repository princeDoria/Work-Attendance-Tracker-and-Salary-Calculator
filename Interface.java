import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Interface extends Data implements ActionListener {
    protected JFrame f1, f2, f3, f4;
    protected JPanel p1, p2, p3, s1, s2, s3;
    protected JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17;
    protected JLabel e1,e2, e3;
    protected JTextField jtr, jtsa, jtsb, jtbase, jtminus, jtbal;
    protected JTextField jtnminus, jtmr, jtadd, jtar, jtns, jtca, jttd, jtth, jttm, jtoth, jtotm;
    protected JTextField jtln, jtfn, jtmn, jtcn, jtbs, jtd, jtb;
    protected JButton mb1, mb2, mb3, back1, back2, back3;
    protected JButton submit1, clear, clear2, calc, change, remove;
    protected JComboBox jcrs, jcss, jcn;
    protected JComboBox jcs, jcr, jcsa, jcsb, jcbm, jcbd, jcby;
    protected JTable employeeList;
    protected DefaultTableModel mTable;
    protected DefaultComboBoxModel<String> model1;
    protected TitledBorder border1, border2, border3;
    Interface(){
        f1 = new JFrame();
        f1.setTitle("CBDJR, Architects");
        f1.setBounds(550,200,500,350);
        f1.setLayout(new BorderLayout(10,5));
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING));
        p2.setLayout(new GridLayout(3,1,10,10));

        l1 = new JLabel("Ceferino B. Doria, Jr.,");
        l2 = new JLabel("Architects");
        l1.setFont(new Font("Arial", Font.BOLD, 25));
        l2.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 25));
        p1.add(l1);
        p1.add(l2);

        mb1 = new JButton("Employees");
        mb2 = new JButton("New Employee");
        mb3 = new JButton("Calculate Salary");
        mb1.setFont(new Font("Arial", Font.PLAIN, 20));
        mb2.setFont(new Font("Arial", Font.PLAIN, 20));
        mb3.setFont(new Font("Arial", Font.PLAIN, 20));

        mb1.addActionListener(this);
        mb2.addActionListener(this);
        mb3.addActionListener(this);

        p2.add(mb1);
        p2.add(mb2);
        p2.add(mb3);

        addEmptySpace();

        f1.add(p1, BorderLayout.NORTH);
        f1.add(p2, BorderLayout.CENTER);
        f1.add(s1, BorderLayout.EAST);
        f1.add(s2, BorderLayout.WEST);
        f1.add(s3, BorderLayout.PAGE_END);
        f1.setVisible(true);

        Employees();
        newEmployee();
        calcSalary();
    }
    public void actionPerformed(ActionEvent ae){
        Object a = ae.getSource();
        if(a == mb1){
            f2.setVisible(true);
            f1.setVisible(false);
        }
        if(a == mb2){
            f3.setVisible(true);
            f1.setVisible(false);
        }
        if(a == mb3){
            f4.setVisible(true);
            f1.setVisible(false);
        }
        if(a == back1){
            f1.setVisible(true);
            f2.setVisible(false);
        }
        if(a == back2){
            f1.setVisible(true);
            f3.setVisible(false);
            emptyNewEmployee();
        }
        if(a == back3){
            f1.setVisible(true);
            f4.setVisible(false);
            emptyCalculate();
        }
        if(a == submit1){
            addEmployee();
        }
        if(a == clear){
            emptyNewEmployee();
        }
        if(a == clear2){
            emptyCalculate();
        }
        if(a == calc){
            calculate();
            printCalc();
        }
        if(a == change){
            switchInput();
        }
        if(a == remove){
            removeRow();
        }
        setBase();
    }
    public void addEmptySpace(){        //empty panels around main panel to mimic border
        s1 = new JPanel();
        s2 = new JPanel();
        s3 = new JPanel();

        e1 = new JLabel("            ");
        e2 = new JLabel("            ");

        s1.add(e1);
        s2.add(e2);
    }
    public void Employees(){            //shows list of employess
        f2 = new JFrame();
        f2.setTitle("CBDJR, Architects");
        f2.setBounds(300,200,1000,300);
        f2.setLayout(new BorderLayout(10,10));
        f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        p2.setLayout(new BorderLayout(5,5));

        l1 = new JLabel("Employee Information: ");
        e3 = new JLabel("                                               " +
                "                                                 ");
        l1.setFont(new Font("Arial", Font.BOLD, 28));
        e3.setFont(new Font("Arial", Font.PLAIN, 20));
        back1 = new JButton("Back");
        p1.add(l1);
        p1.add(e3);
        p1.add(back1);

        back1.addActionListener(this);

        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        l2 = new JLabel("Role: ");
        l3 = new JLabel("Skill: ");
        jcrs = new JComboBox(roleS);
        jcss = new JComboBox(skillS);

        sortPanel.add(l2);
        sortPanel.add(jcrs);
        sortPanel.add(l3);
        sortPanel.add(jcss);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        mTable = new DefaultTableModel(tableHeader, 0);
        employeeList = new JTable(mTable);
        for(int i=3; i<tableHeader.length; i++){
            employeeList.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        employeeList.setRowHeight(20);
        employeeList.getColumn("Suffix").setPreferredWidth(20);
        JScrollPane sp = new JScrollPane(employeeList);

        p2.add(sortPanel, BorderLayout.NORTH);
        p2.add(sp, BorderLayout.CENTER);

        JPanel rmv = new JPanel();
        rmv.setLayout(new FlowLayout(FlowLayout.TRAILING, 50, 5));
        remove = new JButton("Remove Employee");
        remove.addActionListener(this);
        rmv.add(remove);

        addEmptySpace();

        f2.add(p1, BorderLayout.NORTH);
        f2.add(p2, BorderLayout.CENTER);
        f2.add(s1, BorderLayout.EAST);
        f2.add(s2, BorderLayout.WEST);
        f2.add(rmv, BorderLayout.SOUTH);
        f2.setVisible(false);
    }
    public void newEmployee(){          //show window to add new employee
        f3 = new JFrame();
        f3.setTitle("CBDJR, Architects");
        f3.setBounds(500,150,600,350);
        f3.setLayout(new BorderLayout(10,10));
        f3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));
        l1 = new JLabel("New Employee: ");
        e3 = new JLabel("                                                 ");
        back2 = new JButton("Back");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        e3.setFont(new Font("Arial", Font.PLAIN, 20));
        back2.addActionListener(this);
        p1.add(l1);
        p1.add(e3);
        p1.add(back2);

        newEmployeePanel2();

        p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.TRAILING,40,5));
        submit1 = new JButton("Submit");
        submit1.addActionListener(this);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        p3.add(clear);
        p3.add(submit1);

        addEmptySpace();

        f3.add(p1, BorderLayout.NORTH);
        f3.add(p2, BorderLayout.CENTER);
        f3.add(p3, BorderLayout.SOUTH);
        f3.add(s1, BorderLayout.EAST);
        f3.add(s2, BorderLayout.WEST);
        f3.setVisible(false);
    }
    public void newEmployeePanel2(){
        p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));

        String[] days = new String[31];
        for(int i = 0; i < days.length; i++){
            days[i] = Integer.toString(i+1);
        }

        String[] months = new String[12];
        for(int i = 0; i < months.length; i++){
            months[i] = Integer.toString(i+1);
        }

        String[] years = new String[83];
        int year = 1940;
        for(int i = 0; i < years.length; i++){
            years[i] = Integer.toString(year+i);
        }

        l2 = new JLabel("Family Name: ");
        l3 = new JLabel("First Name: ");
        l4 = new JLabel("Middle Initial: ");
        l5 = new JLabel("Suffix: ");
        l6 = new JLabel("Role: ");
        l7 = new JLabel("Skill A: ");
        l8 = new JLabel("Skill B: ");
        l9 = new JLabel("Birthday: ");
        l10 = new JLabel("Contact Number: ");
        l11 = new JLabel("Base Salary: ");
        l12 = new JLabel("Deduction: ");
        l13 = new JLabel("Salary Balance: ");
        JLabel l9a = new JLabel("MM");
        JLabel l9b = new JLabel("DD");
        JLabel l9c = new JLabel("YYYY");

        jtln = new JTextField();         //last name
        jtfn = new JTextField();         //first name
        jtmn = new JTextField();         //middle initial
        jtcn = new JTextField();         //contact number
        jtbs = new JTextField();         //base salary
        jtd = new JTextField();         //deduction
        jtb = new JTextField();         //salary balance

        jcs = new JComboBox(suffix);    //suffix
        jcr = new JComboBox(roleNe);     //role
        jcsa = new JComboBox(skillNe);    //skill a
        jcsb = new JComboBox(skillNe);    //skill b
        jcbm = new JComboBox(months);    //bday month
        jcbd = new JComboBox(days);      //bday day
        jcby = new JComboBox(years);     //bday year

        jcs.setSelectedIndex(-1);
        jcr.setSelectedIndex(-1);
        jcsa.setSelectedIndex(-1);
        jcsb.setSelectedIndex(-1);
        jcbm.setSelectedIndex(-1);
        jcbd.setSelectedIndex(-1);
        jcby.setSelectedIndex(-1);

        JPanel name = new JPanel(new GridLayout(2,4,5,5));
        name.add(l2);
        name.add(jtln);
        name.add(l3);
        name.add(jtfn);
        name.add(l4);
        name.add(jtmn);
        name.add(l5);
        name.add(jcs);
        p2.add(name);

        JPanel job = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        job.add(l6);
        job.add(jcr);
        job.add(l7);
        job.add(jcsa);
        job.add(l8);
        job.add(jcsb);
        p2.add(job);

        JPanel bday1 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        JPanel bday2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        bday1.add(l9);
        bday2.add(l9a);
        bday2.add(jcbm);
        bday2.add(l9b);
        bday2.add(jcbd);
        bday2.add(l9c);
        bday2.add(jcby);
        bday1.add(bday2);
        p2.add(bday1);

        JPanel others = new JPanel(new GridLayout(2,4,5,5));
        others.add(l10);
        others.add(jtcn);
        others.add(l11);
        others.add(jtbs);
        others.add(l12);
        others.add(jtd);
        others.add(l13);
        others.add(jtb);
        p2.add(others);
    }
    public void calcSalary(){
        f4 = new JFrame();
        f4.setTitle("CBDJR, Architects");
        f4.setBounds(450,180, 600, 400);
        f4.setLayout(new BorderLayout(10,5));
        f4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING,20,5));
        l1 = new JLabel("Calculate Salary");
        e3 = new JLabel("                                                   ");
        back3 = new JButton("Back");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        e3.setFont(new Font("Arial", Font.PLAIN, 20));
        back3.addActionListener(this);
        p1.add(l1);
        p1.add(e3);
        p1.add(back3);

        calcSalPanel2();

        p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
        calc = new JButton("Calculate");
        calc.addActionListener(this);
        clear2 = new JButton("Clear");
        clear2.addActionListener(this);
        p3.add(clear2);
        p3.add(calc);

        addEmptySpace();

        f4.add(p1, BorderLayout.NORTH);
        f4.add(p2, BorderLayout.CENTER);
        f4.add(p3, BorderLayout.SOUTH);
        f4.add(s1, BorderLayout.EAST);
        f4.add(s2, BorderLayout.WEST);
        f4.setVisible(false);
    }
    public void calcSalPanel2(){
        p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));

        l2 = new JLabel("Worker: ");
        l3 = new JLabel("Role: ");
        l4 = new JLabel("Skill A: ");
        l5 = new JLabel("Base Salary: ");
        l6 = new JLabel("Deduction: ");
        l7 = new JLabel("Balance: ");
        l9 = new JLabel("Over Time: hr/s");
        l10 = new JLabel("Payroll Deduction: ");
        l11 = new JLabel("Reason: ");
        l12 = new JLabel("Addition: ");
        l13 = new JLabel("Reason: ");
        l14 = new JLabel("New Salary: ");
        l15 = new JLabel("Cash Advance: ");
        l16 = new JLabel("Skill B: ");
        l17 = new JLabel("min/s");
        JLabel l8a = new JLabel("day/s");
        JLabel l8b = new JLabel("hr/s");
        JLabel l8c = new JLabel("min/s");


        jcn = new JComboBox();
        jcn.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        jcn.setSelectedIndex(-1);
        jcn.addActionListener(this);

        jtr = new JTextField("",9);     //role
        jtsa = new JTextField("",8);    //skillA
        jtsb = new JTextField("",8);    //skillB
        jtbase = new JTextField();                  //base salary
        jtminus = new JTextField();                 //deduction bal
        jtbal = new JTextField();                   //balance
        jtnminus = new JTextField();                //deduction
        jtmr = new JTextField();                    //d reason
        jtadd = new JTextField();                   //addition
        jtar = new JTextField();                    //a reason
        jtns = new JTextField();                    //new salary
        jtca = new JTextField();                    //cash advance
        jtoth = new JTextField("",4);   //overtime hours
        jtotm = new JTextField("", 4);  //overtime mins
        jttd = new JTextField("",3);    //days
        jtth = new JTextField("",3);    //hours
        jttm = new JTextField("",3);    //mins

        jtbase.setHorizontalAlignment(SwingConstants.RIGHT);
        jtminus.setHorizontalAlignment(SwingConstants.RIGHT);
        jtbal.setHorizontalAlignment(SwingConstants.RIGHT);
        jtnminus.setHorizontalAlignment(SwingConstants.RIGHT);
        jtmr.setHorizontalAlignment(SwingConstants.RIGHT);
        jtadd.setHorizontalAlignment(SwingConstants.RIGHT);
        jtar.setHorizontalAlignment(SwingConstants.RIGHT);
        jtns.setHorizontalAlignment(SwingConstants.RIGHT);
        jtca.setHorizontalAlignment(SwingConstants.RIGHT);
        jtoth.setHorizontalAlignment(SwingConstants.RIGHT);
        jtotm.setHorizontalAlignment(SwingConstants.RIGHT);
        jttd.setHorizontalAlignment(SwingConstants.RIGHT);
        jtth.setHorizontalAlignment(SwingConstants.RIGHT);
        jttm.setHorizontalAlignment(SwingConstants.RIGHT);

        jtnminus.addActionListener(this);
        jtadd.addActionListener(this);
        jtns.addActionListener(this);
        jtca.addActionListener(this);

        jtr.setEditable(false);
        jtsa.setEditable(false);
        jtsb.setEditable(false);
        jtbase.setEditable(false);
        jtminus.setEditable(false);
        jtbal.setEditable(false);
        jtns.setEditable(false);
        jtca.setEditable(false);

        jtr.setFont(new Font("Arial", Font.PLAIN, 14));
        jtsa.setFont(new Font("Arial", Font.PLAIN, 14));
        jtsb.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel name = new JPanel();
        name.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
        name.add(l2);
        name.add(jcn);

        JPanel job = new JPanel();
        job.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        job.add(l3);
        job.add(jtr);
        job.add(l4);
        job.add(jtsa);
        job.add(l16);
        job.add(jtsb);

        JPanel employee = new JPanel();
        employee.setLayout(new GridLayout(2,1,5,5));
        employee.add(name);
        employee.add(job);

        JPanel sal = new JPanel();
        sal.setLayout(new GridLayout(1,6,5,5));
        sal.add(l5);
        sal.add(jtbase);
        sal.add(l6);
        sal.add(jtminus);
        sal.add(l7);
        sal.add(jtbal);

        JPanel day = new JPanel();
        day.setLayout(new FlowLayout(FlowLayout.LEADING,5,0));
        day.add(l8a);
        day.add(jttd);
        day.add(l8b);
        day.add(jtth);
        day.add(l8c);
        day.add(jttm);

        JPanel ot = new JPanel();
        ot.setLayout(new FlowLayout(FlowLayout.LEADING,5,0));
        ot.add(l9);
        ot.add(jtoth);
        ot.add(l17);
        ot.add(jtotm);

        border1 = BorderFactory.createTitledBorder("Time");
        border1.setTitleJustification(TitledBorder.CENTER);
        JPanel time = new JPanel();
        time.setLayout(new GridLayout(1,3,5,5));
        time.setBorder(border1);
        time.add(day);
        time.add(ot);

        border2 = BorderFactory.createTitledBorder("Standard Payroll");
        border2.setTitleJustification(TitledBorder.CENTER);
        JPanel in1 = new JPanel();
        in1.setLayout(new GridLayout(4,2,5,5));
        in1.setBorder(border2);
        in1.add(l10);
        in1.add(jtnminus);
        in1.add(l11);
        in1.add(jtmr);
        in1.add(l12);
        in1.add(jtadd);
        in1.add(l13);
        in1.add(jtar);

        border3 = BorderFactory.createTitledBorder("Unique Payroll");
        border3.setTitleJustification(TitledBorder.CENTER);
        JPanel in2 = new JPanel();
        in2.setLayout(new GridLayout(2,2,5,5));
        in2.setBorder(border3);
        in2.add(l14);
        in2.add(jtns);
        in2.add(l15);
        in2.add(jtca);

        JPanel sb = new JPanel();
        change = new JButton("Switch");
        change.addActionListener(this);
        sb.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
        sb.add(change);
        JPanel xtra = new JPanel();
        xtra.setLayout(new GridLayout(2,1,5,5));
        xtra.add(in2);
        xtra.add(sb);

        JPanel calc = new JPanel();
        calc.setLayout(new GridLayout(1,2,5,5));
        calc.add(in1);
        calc.add(xtra);

        p2.add(name);
        p2.add(job);
        p2.add(sal);
        p2.add(time);
        p2.add(calc);
    }
    public void addEmployee(){
        String ln = checkNull(jtln.getText());
        String fn = checkNull(jtfn.getText());
        String mn = checkNull(jtmn.getText());
        String cn = checkNull(jtcn.getText());
        String s = checkNull(jcs);
        String r = checkNull(jcr);
        String sa = checkNull(jcsa);
        String sb = checkNull(jcsb);
        String bm = checkNull(jcbm);
        String bd = checkNull(jcbd);
        String by = checkNull(jcby);
        String bdate = bm + " / " + bd + " / " + by;

        int bs = checkNull(jtbs);
        int d = checkNull(jtd);
        int b = checkNull(jtb);

        info[count][0] = ln;
        info[count][1] = fn;
        info[count][2] = mn;
        info[count][3] = s;
        info[count][4] = r;
        info[count][5] = sa;
        info[count][6] = sb;
        info[count][7] = bdate;
        info[count][8] = cn;

        mTable.addRow(new Object[]{ln,fn,mn,s,r,sa,sb,bdate,cn});

        baseS[count][0] = bs;
        baseS[count][1] = d;
        baseS[count][2] = b;

        setName();
        model1 = new DefaultComboBoxModel<>(names);
        jcn.setModel(model1);

        count += 1;

        JOptionPane.showMessageDialog(null,"Employee Added");
    }
    public void emptyNewEmployee(){
        jtln.setText("");
        jtfn.setText("");
        jtmn.setText("");
        jtcn.setText("");
        jtbs.setText("");
        jtd.setText("");
        jtb.setText("");
        jcs.setSelectedIndex(-1);
        jcr.setSelectedIndex(-1);
        jcsa.setSelectedIndex(-1);
        jcsb.setSelectedIndex(-1);
        jcbm.setSelectedIndex(-1);
        jcbd.setSelectedIndex(-1);
        jcby.setSelectedIndex(-1);
    }
    public String checkNull(String s){
        String temp;
        if(s.isEmpty()){
            temp = "";
        }
        else{
            temp = s;
        }
        return temp;
    }
    public String checkNull(JComboBox s){
        String temp;
        if(s.getSelectedItem() == null){
            temp = "";
        }
        else{
            temp = s.getSelectedItem().toString();
        }
        return temp;
    }
    public int checkNull(JTextField s){
        int temp;
        if(s.getText().isEmpty()){
            temp = 0;
        }
        else{
            temp = Integer.parseInt(s.getText());
        }
        return temp;
    }
    public void setName(){
        String[] temp = new String[count + 1];
        for(int i = 0; i < temp.length; i++){
            if(info[i][3]==null || info[i][3].isEmpty()){
                temp[i] = info[i][0] + ", " + info[i][1] + " " + info[i][2];
            }
            else{
                temp[i] = info[i][0] + ", " + info[i][1] + " " + info[i][2] + ", " + info[i][3];
            }
        }
        names = temp;
    }
    public void setBase(){
        String s;
        try{
            s = jcn.getSelectedItem().toString();
            emptyCalculate();
        }
        catch(NullPointerException npe){
            s = "empty";
        }
        for(int i = 0; i < names.length; i++){
            if(s.equals(names[i])){
                jtr.setText(info[i][4]);
                jtsa.setText(info[i][5]);
                jtsb.setText(info[i][6]);
                jtbase.setText("\u20B1 " + baseS[i][0]);
                jtminus.setText("\u20B1 " + baseS[i][1]);
                jtbal.setText("\u20B1 " + baseS[i][2]);
                id = i;
            }
        }
    }
    public void calculate(){
        double base = baseS[id][0];
        double minus = baseS[id][1];
        double bal = baseS[id][2];

        double nminus = Double.valueOf(checkNull(jtnminus));
        double add = Double.valueOf(checkNull(jtadd));
        double ns = Double.valueOf(checkNull(jtns));
        double ca = Double.valueOf(checkNull(jtca));

        double hour = base/8;
        double day = base * Double.valueOf(checkNull(jttd));
        double hrs = (hour) * Double.valueOf(checkNull(jtth));
        double min = (hour/60) * Double.valueOf(checkNull(jttm));
        double oth = (hour) * Double.valueOf(checkNull(jtoth));
        double otm = (hour/60) * Double.valueOf(checkNull(jtotm));

        initial = day + hrs + min + oth + otm;
        finalSal = initial;

        String mr = "", ar = "";
        if(nminus > 0){
            finalSal -= nminus;
            baseS[id][1] -= Math.round(nminus);
            mr = jtmr.getText();
        }
        if(add > 0){
            finalSal += add;
            baseS[id][1] += Math.round(add);
            ar = jtar.getText();
        }

        if(nminus > 0 && add > 0){
            payment = "\nInitial Pay: \u20B1 " + Math.round(initial) +
                    "\nDeduction: " + checkNull(jtnminus) + "\nReason: " + mr +
                    "\nCash Advance: " + checkNull(jtadd) + "\nReason: " + ar +
                    "\nFinal Pay: \u20B1 " + Math.round(finalSal);
        }
        else if(nminus > 0){
            payment = "\nInitial Pay: \u20B1 " + Math.round(initial) +
                    "\nDeduction: " + checkNull(jtnminus) + "\nReason: " + mr +
                    "\nFinal Pay: \u20B1 " + Math.round(finalSal);
        }
        else if(add > 0){
            payment = "\nInitial Pay: \u20B1 " + Math.round(initial) +
                    "\nCash Advance: " + checkNull(jtadd) + "\nReason: " + ar +
                    "\nFinal Pay: " + Math.round(finalSal);
        }
        else{
            payment = "\nFinal Pay: \u20B1 " + Math.round(finalSal);
        }

        if(ns > 0){
            finalSal = ns;
            if(bal > 0){
                baseS[id][2] -= ns;
                payment = "\nInitial Pay: \u20B1 " + Math.round(finalSal);
            }
            else{
                baseS[id][2] += (initial - ns);
                payment = "\nFinal Pay: \u20B1 " + Math.round(finalSal);
            }
        }
        if(ca > 0){
            finalSal += ca;
            baseS[id][2] += (initial - ca);
            payment += "\nFinal Pay: \u20B1 " + Math.round(finalSal);
        }
    }
    public void printCalc(){
        String s;
        if(checkNull(jtth) == 0 && checkNull(jttm) == 0){
            s = jcn.getSelectedItem().toString() + "\nDay/s: " + checkNull(jttd);
        }
        else if(checkNull(jtth) == 0){
            s = jcn.getSelectedItem().toString() + "\nDay/s: " + checkNull(jttd) + "  Min/s: " + checkNull(jttm);
        }
        else if(checkNull(jttm) == 0){
            s = jcn.getSelectedItem().toString() + "\nDay/s: " + checkNull(jttd) + "  Hour/s: " + checkNull(jtth);
        }
        else{
            s = jcn.getSelectedItem().toString() + "\nDay/s: " + checkNull(jttd) + "  Hour/s: " + checkNull(jtth) +
                    "  Min/s: " + checkNull(jttm);
        }

        if(checkNull(jtoth) > 0 && checkNull(jtotm) > 0){
            JOptionPane.showMessageDialog(null,s + "\nOvertime: " +
                    checkNull(jtoth) + " hr/s  " + checkNull(jtotm) + " min/s " + payment);
        }
        else if(checkNull(jtoth) > 0){
            JOptionPane.showMessageDialog(null,s + "\nOvertime: " +
                    checkNull(jtoth) + " hr/s  " + payment);
        }
        else if(checkNull(jtotm) > 0){
            JOptionPane.showMessageDialog(null,s + "\nOvertime: " +
                    checkNull(jtotm) + " min/s  " + payment);
        }
        else{
            JOptionPane.showMessageDialog(null,s + payment);
        }
    }
    public void emptyCalculate(){
        jtnminus.setText("");
        jtmr.setText("");
        jtadd.setText("");
        jtar.setText("");
        jtns.setText("");
        jtca.setText("");
        jtoth.setText("");
        jtotm.setText("");
        jttd.setText("");
        jtth.setText("");
        jttm.setText("");
    }
    public void switchInput(){
        if(!jtns.isEditable()){
            jtnminus.setEditable(false);
            jtmr.setEditable(false);
            jtadd.setEditable(false);
            jtar.setEditable(false);
            jtns.setEditable(true);
            jtca.setEditable(true);
        }
        else{
            jtnminus.setEditable(true);
            jtmr.setEditable(true);
            jtadd.setEditable(true);
            jtar.setEditable(true);
            jtns.setEditable(false);
            jtca.setEditable(false);
        }
        jtnminus.setText("");
        jtmr.setText("");
        jtadd.setText("");
        jtar.setText("");
        jtns.setText("");
        jtca.setText("");
    }
    public void removeRow(){
        if(employeeList.getSelectedRowCount() == 1){
            int c = employeeList.getSelectedRow();

            mTable.removeRow(employeeList.getSelectedRow());

            refresh(c);

            count -=1;
            System.out.println(c);
        }
        else{
            if(employeeList.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No Employees!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select One (1) employee.");
            }
        }
    }
    public void refresh(int a){
        for(int i = a; i < employeeList.getRowCount(); i++){
            for(int x = 0; x < tableHeader.length; x++){
                info[i][x] = info[i+1][x];
            }
        }
        for(int i = a; i < employeeList.getRowCount(); i++){
            for(int x = 0; x < 3; x++){
                baseS[i][x] = baseS[i+1][x];
            }
        }
        for(int i = a; i < employeeList.getRowCount(); i++){
            names[i] = names[i+1];
        }
        String[] newN = new String[names.length-1];
        for(int i = 0; i < newN.length; i++){
            newN[i] = names[i];
        }
        DefaultComboBoxModel<String> newModel1 = new DefaultComboBoxModel<>(newN);
        model1 = newModel1;
        jcn.setModel(model1);
    }
}
