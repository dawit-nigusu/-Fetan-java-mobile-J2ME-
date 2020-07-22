import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.*;
import java.lang.Math;
import javax.microedition.location.*;
import java.io.*;
import javax.microedition.io.*;
import com.sun.lwuit.*;
import com.sun.lwuit.events.*;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.animations.*;
import com.sun.lwuit.plaf.*;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.MIDletStateChangeException;

public class Derash extends MIDlet implements ActionListener {
    
    RecordStore HospitalsName;
    RecordStore HospitalLongitude;
    RecordStore HospitalLatitude;
    RecordStore HospitalCell;
    RecordStore PolicesName;
    RecordStore PoliceLongitude;
    RecordStore PoliceLatitude;
    RecordStore PoliceCell;
    RecordStore FiresName;
    RecordStore FireLongitude;
    RecordStore FireLatitude;
    RecordStore FireCell;
    RecordStore ElectricsName;
    RecordStore ElectricLongitude;
    RecordStore ElectricLatitude;
    RecordStore ElectricCell;
    Command back;
    Command Exit;
    Command help;
    Command about;
    Command doc;
    Command di;
    Command backmap;
    Command backhelp;
    Image roha;
    Image roh;
    Image fir_icon ;
    Image pol_icon;
    Image elec_icon;
    Image hos_icon ;
    Image dt;
    Image abo;
    Image document;
    Image hel;
    Image cl;
    Image mp;
    Image backup;
    String url;
    String demo;
    String[] sortedlist;
    Form optionform;
    Form listform;
    Form map;
    Form ff;
    Form allinone;
    RadioButton rb;
    ButtonGroup bg = new ButtonGroup();
    Form fr;
    Form f;
    List helplist;
    List callorview;
    double personlat;
    double personlong;
    double EarthDiameter = 12742;
    Location currentLocation;
    LocationProvider locationProvider;
    QualifiedCoordinates location;
   
    Command showmap = new Command("በካርታ ላይ አሳይ"){public void actionPerformed(ActionEvent ae){
        try {
            
            locationProvider = LocationProvider.getInstance(null);
            currentLocation = locationProvider.getLocation(60);
        location = currentLocation.getQualifiedCoordinates();
        personlong = location.getLongitude();
        personlat = location.getLatitude();
            if (demo.equals("ሆስፒታል")){
                int in = bg.getSelectedIndex();
                String so = sortedlist[in];
                try{
           
                int id = findhospitalid(so);
                byte[] dre = HospitalLongitude.getRecord(id);
                byte[] dra = HospitalLatitude.getRecord(id);

                String dea = new String(dre);
                String dei = new String(dra);
                double placeo = Double.parseDouble(dea);
                double placea = Double.parseDouble(dei);
                showmap(placea,placeo,personlat,personlong);}catch(Exception e){System.out.println("Henok");}
             }
            else  if (demo.equals("ፖሊስ")){
                System.out.println(bg.getSelectedIndex());
                int in = bg.getSelectedIndex();
                String so = sortedlist[in];
                try{
                int id = findpoliceid(so);
                byte[] dre = PoliceLongitude.getRecord(id);
                byte[] dra = PoliceLatitude.getRecord(id);

                String dea = new String(dre);
                String dei = new String(dra);
                double placeo = Double.parseDouble(dea);
                double placea = Double.parseDouble(dei);
                showmap(placea,placeo,personlat,personlong);}catch(Exception e){System.out.println("Henok");}
             }
            else  if (demo.equals("እሳት አደጋ")){
                System.out.println(bg.getSelectedIndex());
                int in = bg.getSelectedIndex();
                String so = sortedlist[in];System.out.println("e");
                try{
                int id = findfireid(so);System.out.println("e");
                byte[] dre = FireLongitude.getRecord(id);System.out.println("e");
                byte[] dra = FireLatitude.getRecord(id);
System.out.println("e");
                String dea = new String(dre);
                String dei = new String(dra);
                double placeo = Double.parseDouble(dea);System.out.println("e");
                double placea = Double.parseDouble(dei);
                showmap(placea,placeo,personlat,personlong);}catch(Exception e){System.out.println(e);}
             }
            else  if (demo.equals("ኤልፓ")){
                System.out.println(bg.getSelectedIndex());
                int in = bg.getSelectedIndex();
                String so = sortedlist[in];
                try{
                int id = findelectricid(so);
                byte[] dre = ElectricLongitude.getRecord(id);
                byte[] dra = ElectricLatitude.getRecord(id);

                String dea = new String(dre);
                String dei = new String(dra);
                double placeo = Double.parseDouble(dea);
                double placea = Double.parseDouble(dei);
                showmap(placea,placeo,personlat,personlong);}catch(Exception e){System.out.println("Henok");}}} catch (Exception ex) {}}};
    
    Command callplace = new Command("ደውል"){public void actionPerformed(ActionEvent ea){
    
    String demoo = fr.getSelectCommandText();
        if(demoo.equals("ሆስፒታል")){
            int i = bg.getSelectedIndex();
            String fs= sortedlist[i];
            try{
                int id = findhospitalid(fs);
                byte[] pn = HospitalCell.getRecord(id);
                String s = new String(pn);
                double dse = Double.parseDouble(s);
                platformRequest("tel:"+dse);
            }
        catch(Exception e){System.out.println(e);}
    }
        if(demoo.equals("ኤልፓ")){
            int idr = bg.getSelectedIndex();
            String fss= sortedlist[idr];
            try{
                int ids = findhospitalid(fss);
                byte[] pn = ElectricCell.getRecord(ids);
                String s = new String(pn);
                double dse = Double.parseDouble(s);
                platformRequest("tel:"+dse);
            }
            catch(Exception e){System.out.println(e);}
        }
        if(demoo.equals("እሳት አደጋ")){
            int wi = bg.getSelectedIndex();
            String wfs= sortedlist[wi];
            try{
                int wid = findhospitalid(wfs);
                byte[] pn = FireCell.getRecord(wid);
                String s = new String(pn);
                double dse = Double.parseDouble(s);
                platformRequest("tel:"+dse);
            }
            catch(Exception e){System.out.println(e);}
        }
        if(demoo.equals("ፖሊስ")){
            int ei = bg.getSelectedIndex();
            String efs= sortedlist[ei];
            try{
                int eid = findhospitalid(efs);
                byte[] pn = PoliceCell.getRecord(eid);
                String s = new String(pn);
                double dse = Double.parseDouble(s);
                platformRequest("tel:"+dse);}catch(Exception e){System.out.println(e);}}}};
    
public void base() throws Exception{

    HospitalsName = RecordStore.openRecordStore("HospitalsName",true);
    HospitalLongitude = RecordStore.openRecordStore("HospitalLongitude", true);
    HospitalLatitude = RecordStore.openRecordStore("HospitalLatitude", true);
    HospitalCell = RecordStore.openRecordStore("HospitalCell", true);
    ElectricsName = RecordStore.openRecordStore("ElectricsName",true);
    ElectricLongitude = RecordStore.openRecordStore("ElectricLongitude",true);
    ElectricLatitude = RecordStore.openRecordStore("ElectricLatitude",true);
    ElectricCell = RecordStore.openRecordStore("ElectricCell",true);
    PolicesName = RecordStore.openRecordStore("PolicesName",true);
    PoliceLongitude = RecordStore.openRecordStore("PoliceLongitude", true);
    PoliceLatitude = RecordStore.openRecordStore("PoliceLatitude", true);
    PoliceCell = RecordStore.openRecordStore("PoliceCell", true);
    FiresName = RecordStore.openRecordStore("FiresName",true);
    FireLongitude = RecordStore.openRecordStore("FireLongitude",true);
    FireLatitude = RecordStore.openRecordStore("FireLatitude", true);
    FireCell = RecordStore.openRecordStore("FireCell",true);

    try {} catch (Exception e) {}
}

public void addhospital(String n,double la,double lo,int c) throws Exception{
    
    byte[] naming = n.getBytes();
    byte[] loging = String.valueOf(lo).getBytes();
    byte[] laging = String.valueOf(la).getBytes();
    byte[] celling = String.valueOf(c).getBytes();
    HospitalsName.addRecord(naming, 0, naming.length);
    HospitalLongitude.addRecord(loging, 0, loging.length);
    HospitalLatitude.addRecord(laging, 0, laging.length);
    HospitalCell.addRecord(celling, 0, celling.length);
    System.out.println(HospitalsName.getNumRecords());
}

public void addpolice(String n,double la,double lo,int c) throws Exception{
           
    byte[] naming = n.getBytes();
    byte[] loging = String.valueOf(lo).getBytes();
    byte[] laging = String.valueOf(la).getBytes();
    byte[] celling = String.valueOf(c).getBytes();
    PolicesName.addRecord(naming, 0, naming.length);
    PoliceLongitude.addRecord(loging, 0, loging.length);
    PoliceLatitude.addRecord(laging, 0, laging.length);
    PoliceCell.addRecord(celling, 0, celling.length);
    System.out.println(PolicesName.getNumRecords());
} 

public void addelectric(String n,double la,double lo,int c) throws Exception{

    byte[] naming = n.getBytes();
    byte[] loging = String.valueOf(lo).getBytes();
    byte[] laging = String.valueOf(la).getBytes();
    byte[] celling = String.valueOf(c).getBytes();
    ElectricsName.addRecord(naming, 0, naming.length);
    ElectricLongitude.addRecord(loging, 0, loging.length);
    ElectricLatitude.addRecord(laging, 0, laging.length);
    ElectricCell.addRecord(celling, 0, celling.length);
    System.out.println(ElectricsName.getNumRecords());
}

public void addfire(String n,double la,double lo,int c) throws Exception{

    byte[] naming = n.getBytes();
    byte[] loging = String.valueOf(lo).getBytes();
    byte[] laging = String.valueOf(la).getBytes();
    byte[] celling = String.valueOf(c).getBytes();
    FiresName.addRecord(naming, 0, naming.length);
    FireLongitude.addRecord(loging, 0, loging.length);
    FireLatitude.addRecord(laging, 0, laging.length);
    FireCell.addRecord(celling, 0, celling.length);
    System.out.println(FiresName.getNumRecords());
} 

public double CalculateDistance(double laa,double loo,double placelaa,double placeloo) throws Exception{
    
    double distance = 0.5 - Math.cos((placelaa - laa) * Math.PI / 180)/2 + Math.cos(laa * Math.PI / 180) * Math.cos(placelaa * Math.PI / 180) * (1 - Math.cos((placeloo - loo) * Math.PI / 180))/2;
    double answer =  EarthDiameter * asin(Math.sqrt(distance));
    return answer;
}

public void CalculateAllhospitalDistances() throws Exception{
    
    String dm;
    String sm;
    String add;
    int j;
    int r = HospitalsName.getNumRecords();
    double[] calculated_distances = new double[r] ;
    sortedlist = new String[r];
    locationProvider = LocationProvider.getInstance(null);
    currentLocation = locationProvider.getLocation(60);
    location = currentLocation.getQualifiedCoordinates();
    personlong = location.getLongitude();
    personlat = location.getLatitude();
    
    for(int i=1; i<=HospitalsName.getNumRecords();i++){
            
        byte[] py = HospitalLongitude.getRecord(i);
        String lon = new String(py);
        double placelong = Double.parseDouble(lon);

        byte[] pr = HospitalLatitude.getRecord(i);
        String lan = new String(pr);
        double placelat = Double.parseDouble(lan);

        double distance = CalculateDistance(personlat,personlong,placelat,placelong);
        calculated_distances[i-1] = distance;

        byte[] idd = HospitalsName.getRecord(i);
        String nam = new String(idd);
        sortedlist[i-1] = nam;
        String HosName = "" + nam;
    }
    
    SortIt(calculated_distances,sortedlist);
    di = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){f.show();}};
    di.setRolloverIcon(backup);
    allinone = new Form("አገልግሎቶች");
    allinone.addCommand(di);
    allinone.addCommand(callplace);
    allinone.addCommand(showmap);
    allinone.addCommandListener(this);
    
    for (int i=0;i<calculated_distances.length;i++){
        
        System.out.println("The List Of Hospitals Sorted By Distance " + sortedlist[i] + " With Distance Of " + calculated_distances[i]);
        dm = String.valueOf(calculated_distances[i]);
        sm = sortedlist[i];
        j = i+1;
        add = j+". "+sm+"\n"+dm+"   Kilo Meters";
        
        if(i<5){
            rb = new RadioButton(add);
            bg.add(rb);
            allinone.addComponent(rb);
        }
    }
                allinone.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
    allinone.show();
}

public void CalculateAllpoliceDistances() throws Exception{

    String dm;
    String sm;
    String add;
    int j;
    int r = PolicesName.getNumRecords();
    double[] calculated_distances = new double[r] ;
    sortedlist = new String[r];
    locationProvider = LocationProvider.getInstance(null);
    currentLocation = locationProvider.getLocation(60);
    location = currentLocation.getQualifiedCoordinates();
    personlong = location.getLongitude();
    personlat = location.getLatitude();
    
    for(int i=1; i<=PolicesName.getNumRecords();i++){
            
        byte[] py = PoliceLongitude.getRecord(i);
        String lon = new String(py);
        double placelong = Double.parseDouble(lon);

        byte[] pr = PoliceLatitude.getRecord(i);
        String lan = new String(pr);
        double placelat = Double.parseDouble(lan);

        double distance = CalculateDistance(personlat,personlong,placelat,placelong);
        calculated_distances[i-1] = distance;

        byte[] idd = PolicesName.getRecord(i);
        String nam = new String(idd);
        sortedlist[i-1] = nam;
        String HosName = "" + nam;
    }
    
    SortIt(calculated_distances,sortedlist);
    di = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){f.show();}};
   di.setRolloverIcon(backup);
    allinone = new Form("አገልግሎቶች");
    allinone.addCommand(di);
    allinone.addCommand(callplace);
    allinone.addCommand(showmap);
    
    allinone.addCommandListener(this);
    
    for (int i=0;i<calculated_distances.length;i++){
        
        System.out.println("The List Of Polices Sorted By Distance " + sortedlist[i] + " With Distance Of " + calculated_distances[i]);
        dm = String.valueOf(calculated_distances[i]);
        sm = sortedlist[i];
        j = i+1;
        add = j+". "+sm+"\n"+dm+"   Kilo Meters";
        
        if(i<5){
            rb = new RadioButton(add);
            bg.add(rb);
            allinone.addComponent(rb);
        }
    }
                allinone.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
    allinone.show();
}

public void CalculateAllfireDistances() throws Exception{

    String dm;
    String sm;
    String add;
    int j;
    int r = FiresName.getNumRecords();
    double[] calculated_distances = new double[r] ;
    sortedlist = new String[r];
    locationProvider = LocationProvider.getInstance(null);
    currentLocation = locationProvider.getLocation(60);
    location = currentLocation.getQualifiedCoordinates();
    personlong = location.getLongitude();
    personlat = location.getLatitude();
    
    for(int i=1; i<=FiresName.getNumRecords();i++){
            
        byte[] py = FireLongitude.getRecord(i);
        String lon = new String(py);
        double placelong = Double.parseDouble(lon);

        byte[] pr = FireLatitude.getRecord(i);
        String lan = new String(pr);
        double placelat = Double.parseDouble(lan);

        double distance = CalculateDistance(personlat,personlong,placelat,placelong);
        calculated_distances[i-1] = distance;

        byte[] idd = FiresName.getRecord(i);
        String nam = new String(idd);
        sortedlist[i-1] = nam;
        String HosName = "" + nam;
    }
    
    SortIt(calculated_distances,sortedlist);
    di = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){f.show();}};
   di.setRolloverIcon(backup);
    allinone = new Form("አገልግሎቶች");
    allinone.addCommand(di);
    allinone.addCommand(callplace);
    allinone.addCommand(showmap);

    allinone.addCommandListener(this);
    
    for (int i=0;i<calculated_distances.length;i++){
        
        System.out.println("The List Of Fire Sorted By Distance " + sortedlist[i] + " With Distance Of " + calculated_distances[i]);
        dm = String.valueOf(calculated_distances[i]);
        sm = sortedlist[i];
        j = i+1;
        add = j+". "+sm+"\n"+dm+"   Kilo Meters";
        
        if(i<5){
            rb = new RadioButton(add);
            bg.add(rb);
            allinone.addComponent(rb);
        }
    }
                allinone.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
    allinone.show();
}

public void CalculateAllelectricDistances() throws Exception{

    String dm;
    String sm;
    String add;
    int j;
    int r = ElectricsName.getNumRecords();
    double[] calculated_distances = new double[r] ;
    sortedlist = new String[r];
    locationProvider = LocationProvider.getInstance(null);
    currentLocation = locationProvider.getLocation(60);
    location = currentLocation.getQualifiedCoordinates();
    personlong = location.getLongitude();
    personlat = location.getLatitude();
    
    for(int i=1; i<=ElectricsName.getNumRecords();i++){
            
        byte[] py = ElectricLongitude.getRecord(i);
        String lon = new String(py);
        double placelong = Double.parseDouble(lon);

        byte[] pr = ElectricLatitude.getRecord(i);
        String lan = new String(pr);
        double placelat = Double.parseDouble(lan);

        double distance = CalculateDistance(personlat,personlong,placelat,placelong);
        calculated_distances[i-1] = distance;

        byte[] idd = ElectricsName.getRecord(i);
        String nam = new String(idd);
        sortedlist[i-1] = nam;
        String HosName = "" + nam;
    }
    
    SortIt(calculated_distances,sortedlist);
    di = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){f.show();}};
   di.setRolloverIcon(backup);
    allinone = new Form("አገልግሎቶች");
    allinone.addCommand(di);
    allinone.addCommand(callplace);
    allinone.addCommand(showmap);

    allinone.addCommandListener(this);
    
    for (int i=0;i<calculated_distances.length;i++){
        
        System.out.println("The List Of Hosptials Sorted By Distance " + sortedlist[i] + " With Distance Of " + calculated_distances[i]);
        dm = String.valueOf(calculated_distances[i]);
        sm = sortedlist[i];
        j = i+1;
        add = j+". "+sm+"\n"+dm+"   Kilo Meters";
        
        if(i<5){
            rb = new RadioButton(add);
            bg.add(rb);
            allinone.addComponent(rb);
        }
    }
                allinone.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
    allinone.show();
}

public void showmap(double placelat,double placelong,double personlat,double personlong) throws Exception{
    
    Image image = null;
    HttpsConnection http;
    backmap = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){allinone.show();}};
    String url1 = "https://maps.googleapis.com/maps/api/staticmap?size=230x252&maptype"+
                        "=roadmap&markers=color:blue%7Clabel:S%7C"+placelong+","+placelat+"&markers=color:red%7Clabel:G%7C"+personlong+","+personlat;
    InputStream is;
    System.out.println(url1);    
    try{
        http = (HttpsConnection)Connector.open(url1);
        http.setRequestMethod(HttpConnection.GET);
        
        int respCode = http.getResponseCode();
        if (respCode == http.HTTP_OK) {
            StringBuffer sb = new StringBuffer();
            image = Image.createImage(http.openInputStream());
            ff = new Form("Location");
            ff.setBgImage(image);
            int hg = ff.getHeight();
            int wd = ff.getWidth();
            ff.addCommand(backmap);
            ff.addCommandListener(this);
            ff.show();
            ff.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
      }
        }
    catch(Exception e){System.out.println(e);}

}

public int findhospitalid(String idd) throws Exception{
    
    int sampleid = 1;
    String retrieved_string;
    byte[] retrieved_data;
    int idm =0;
    
    for(int i=1; i<=HospitalsName.getNumRecords();i++){
        retrieved_data = HospitalsName.getRecord(i);
        retrieved_string = new String(retrieved_data);
        
        if(retrieved_string.equals(idd)){
                    idm = i;
        }
    }
    return idm;
}

public int findfireid(String idd) throws Exception{
    
    int sampleid = 1;
    String retrieved_string;
    byte[] retrieved_data;
    int idm =0;
    
    for(int i=1; i<=FiresName.getNumRecords();i++){
        retrieved_data = FiresName.getRecord(i);
        retrieved_string = new String(retrieved_data);
        
        if(retrieved_string.equals(idd)){
                    idm = i;
        }
    }
    return idm;
}

public int findelectricid(String idd) throws Exception{
    
    int sampleid = 1;
    String retrieved_string;
    byte[] retrieved_data;
    int idm =0;
    
    for(int i=1; i<=ElectricsName.getNumRecords();i++){
        retrieved_data = ElectricsName.getRecord(i);
        retrieved_string = new String(retrieved_data);
        
        if(retrieved_string.equals(idd)){
                    idm = i;
        }
    }
    return idm;
}

public int findpoliceid(String idd) throws Exception{
    
    int sampleid = 1;
    String retrieved_string;
    byte[] retrieved_data;
    int idm =0;
    
    for(int i=1; i<=PolicesName.getNumRecords();i++){
        retrieved_data = PolicesName.getRecord(i);
        retrieved_string = new String(retrieved_data);
        
        if(retrieved_string.equals(idd)){
                    idm = i;
        }
    }
    return idm;
}

public void SortIt(double[] calculated_distances,String[] sortedlist) throws Exception{

    for(int i=0; i<calculated_distances.length;i++){
        for(int j=0;j<calculated_distances.length;j++){
            if (calculated_distances[i]<calculated_distances[j]){
                double temp = calculated_distances[i];
                calculated_distances[i] = calculated_distances[j];
                calculated_distances[j] = temp;

                String tempo = sortedlist[i];
                sortedlist[i] = sortedlist[j];
                sortedlist[j] = tempo;
            }
        }
    }
}

public static double asin(double a){
    final double epsilon=1.0E-7; 
    double x=a;
    do x-=(Math.sin(x)-a)/Math.cos(x);
    while (Math.abs(Math.sin(x)-a)>epsilon);
    return x;
}

    public void startApp() {
        
        try{
            Display.init(this);
            
            base();
            fr = new Form();
            Form ffl = new Form("");
            Image img = Image.createImage("/roha.jpg");
            ffl.setBgImage(img);
            ffl.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL,true,2500));
            ffl.show();
            Thread.sleep(3000); //Here you set needed time or make a constant


           abo = Image.createImage("/about.png");
           dt = Image.createImage("/exit.png");
           backup = Image.createImage("/backup.png");
           hel = Image.createImage("/help.png");
           document = Image.createImage("/documentation.png");
           roh = Image.createImage("/em.jpg");
           fir_icon = Image.createImage("/fire.png");
           pol_icon = Image.createImage("/police.png");
           elec_icon = Image.createImage("/electric.png");
           hos_icon = Image.createImage("/hospital.png");
                    cl = Image.createImage("/cl.PNG");mp = Image.createImage("/mp.png");showmap.setRolloverIcon(mp);callplace.setRolloverIcon(cl);}catch(Exception e){}

            f = new Form("");
            f.setBgImage(roh);
            
            f.setLayout(new GridLayout(2, 2));

            Button b = new Button("እሳት አደጋ",fir_icon);
            Button b2 = new Button("ፖሊስ",pol_icon);
            Button b3 = new Button("ኤልፓ",elec_icon);
            Button b4 = new Button("ሆስፒታል",hos_icon);
            
            b.getSelectedStyle().setBorder(null, true);
            b4.getSelectedStyle().setBorder(null, true);
            b2.getSelectedStyle().setBorder(null, true);
            b3.getSelectedStyle().setBorder(null, true);
            
            b.getSelectedStyle().setBgColor(0xffffff, true);
            b4.getSelectedStyle().setBgColor(0xffffff, true);
            b2.getSelectedStyle().setBgColor(0xffffff, true);
            b3.getSelectedStyle().setBgColor(0xffffff, true);
            
            b.getSelectedStyle().setBorder(Border.createBevelRaised());
            b4.getSelectedStyle().setBorder(Border.createBevelRaised());
            b2.getSelectedStyle().setBorder(Border.createBevelRaised());
            b3.getSelectedStyle().setBorder(Border.createBevelRaised());
            
            b.getSelectedStyle().setBgTransparency(55, true);
            b2.getSelectedStyle().setBgTransparency(55, true);
            b3.getSelectedStyle().setBgTransparency(55, true);
            b4.getSelectedStyle().setBgTransparency(55, true);
                    
            b.getSelectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE), true);
            b2.getSelectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE), true);
            b3.getSelectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE), true);
            b4.getSelectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE), true);
            
            b.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae){try {
            CalculateAllfireDistances(); demo = "እሳት አደጋ";} catch (Exception ex) {ex.printStackTrace();}}});
           
            b2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae){try {
            CalculateAllpoliceDistances();demo = "ፖሊስ";} catch (Exception ex) {System.out.println(ex);}}});
                 
             b3.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae){try {
            CalculateAllelectricDistances();demo = "ኤልፓ";} catch (Exception ex) {System.out.println(ex);}}});
      
            b4.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae){try {
            CalculateAllhospitalDistances();demo = "ሆስፒታል";} catch (Exception ex) {ex.printStackTrace();}}});
            
            b4.getStyle().setBorder(null);
            b3.getStyle().setBorder(null);
            b2.getStyle().setBorder(null);
            b.getStyle().setBorder(null);

            b4.getStyle().setBgTransparency(0);
            b3.getStyle().setBgTransparency(0);
            b2.getStyle().setBgTransparency(0);
            b.getStyle().setBgTransparency(0);

            b.setTextPosition(Component.BOTTOM);
            b2.setTextPosition(Component.BOTTOM);
            b3.setTextPosition(Component.BOTTOM);
            b4.setTextPosition(Component.BOTTOM);

            f.addComponent(b4);
            f.addComponent(b3);
            f.addComponent(b2);
            f.addComponent(b);
            
            String bi = "ይህ አፕሊኬሽን የተሰራው በማንኛውም ጊዜ እርሶ እንዲጠቀሙት ነው።\n" +
                                "አፕሊኬሽኑን ይክፈቱትና የሚፈልጉትን አገልግሎት ይምረጡ ከዚያም የሚቀርቦትን \n" +
                                "አገልግሎት ሰጪ ተቋም በካርታ ላይ ማየት\n" +
                                "ወይም በስልክ ቁጥራቸው መደወል ይችላሉ።.";
        TextArea t = new TextArea(bi);
        fr.addComponent(t);
        backhelp = new Command("ተመለስ"){public void actionPerformed(ActionEvent ae){f.show();}};
        fr.addCommand(backhelp);
        fr.addCommandListener(this);
                    fr.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
         
        final Form fl = new Form("Center");
        String n = "ይህ አፕሊኬሽን በአዲስ አበባ ቴክኖሎጂ ኢንስቲትዩት\n" +
                        "ሶፍትዌር ኢንጅነሪንግ ዲፓርትመንት 2ኛ አመት ተማሪዎች የተሰራ ነው።\n" +
                        " \n" +
                        "1.ሄኖክ ጌታቸው \n" +
                        "2.ሚክያስ ብርሃኑ \n" +
                        "3.ይስሐቅ አብረሃም\n" +
                        "\n" +
                        "አድራሻ - rohasoftwaresolutions@gmail.com\n" +
                        "ሁሉም መብቶች በሕግ የተጠበቁ ናቸው። 2007 አ.ም";
        TextArea ta =  new TextArea(n);
        fl.addComponent(ta);
        fl.addCommand(backhelp);
        fl.addCommandListener(this);
                    fl.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
        
        final Form ftr = new Form("Center");
        String na = "ይሄ የመጀመሪያው የ \"ደራሽ\" ቨርዥን ነው።";
        TextArea tea  = new TextArea(na);
        ftr.addComponent(tea);
        ftr.addCommand(backhelp);
        ftr.addCommandListener(this);
                    ftr.setTransitionOutAnimator(
            CommonTransitions.createSlide(
                CommonTransitions.SLIDE_HORIZONTAL, true, 1500));
            
            Exit = new Command("ውጣ"){public void actionPerformed(ActionEvent ae){notifyDestroyed();}};
            help = new Command("እርዳታ?"){public void actionPerformed(ActionEvent ae){fr.show();}};
            about = new Command("ስለ እኛ"){public void actionPerformed(ActionEvent ae){fl.show();}};
            doc = new Command("አፕሊኬሽኑ"){public void actionPerformed(ActionEvent ae){ftr.show();}}; 
            
           Exit.setRolloverIcon(dt);
           help.setRolloverIcon(hel);
           about.setRolloverIcon(abo);
           doc.setRolloverIcon(document);
            
            f.addCommand(Exit);
            f.addCommand(doc);
            f.addCommand(help);
            f.addCommand(about);
            f.setTransitionOutAnimator(
            CommonTransitions.createSlide(
            CommonTransitions.SLIDE_HORIZONTAL, false, 2500));
            f.addCommandListener(this);
            
            f.show();
             try{
                addpolice("Tor Hayloch Police",9.010567,38.719956, 920751501);
        addpolice("Megenagna Police",8.012103,38.715289,911340715);
        addpolice("Mexico Police",10.012103,38.715289,912097276);
        addpolice("Bole Police",11.012103,38.715289,911340715);
        addpolice("AAIT Police",6.010567,38.719956, 920751501);
        addpolice("AAU Police",2.012103,38.715289,911340715);
        addpolice("Dire Police",1.012103,38.715289,912097276);
        addpolice("Awasa Police",12.012103,38.715289,911340715);
                addhospital("Tikur Ambessa",9.010567,38.719956, 920751501);
        addhospital("Addis Clinic",8.012103,38.715289,911340715);
        addhospital("Minilik Hospital ",10.012103,38.715289,912097276);
        addhospital("Yekatit Hospital",11.012103,38.715289,911340715);
        addhospital("AAIT Hospital",6.010567,38.719956, 920751501);
        addhospital("AAU Hospital",2.012103,38.715289,911340715);

                addfire("Tor Hayloch Fire Department",9.010567,38.719956, 920751501);
        addfire("4 Kilo Fire Department",8.012103,38.715289,911340715);
        addfire("5 Kilo Fire Department",10.012103,38.715289,912097276);
        addfire("6 Kilo Fire Department",11.012103,38.715289,911340715);
        addfire("AAIT Fire Department",6.010567,38.719956, 920751501);
        addfire("AAU Fire Department",2.012103,38.715289,911340715);
            
                addelectric("Tor Hayloch Electric Power",9.010567,38.719956, 920751501);
        addelectric("4 Kilo Electric Power",8.012103,38.715289,911340715);
        addelectric("5 Kilo Electric Power",10.012103,38.715289,912097276);
        addelectric("6 Kilo Electric Power",11.012103,38.715289,911340715);
        addelectric("AAIT Electric Power",6.010567,38.719956, 920751501);
        addelectric("AAU Electric Power",2.012103,38.715289,911340715);}catch(Exception e){} 
        }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void pauseApp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actionPerformed(ActionEvent ae) {
       
    }
    }