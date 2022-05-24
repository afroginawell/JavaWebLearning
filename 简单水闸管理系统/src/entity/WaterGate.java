package entity;


/**
 * @author xie
 * @create 2022-05-19-14:12
 */
public class WaterGate {

    private String watergateid, watergatename, build_time, principal, longitude, latitude, introduction;

    public WaterGate(){
        watergateid = "";
        watergatename = "";
        build_time = "";
        principal = "";
        longitude = "";
        latitude = "";
        introduction = "";
    }

    public String getWatergateid() {
        return watergateid;
    }

    public void setWatergateid(String watergateid) {
        this.watergateid = watergateid;
    }

    public String getWatergatename() {
        return watergatename;
    }

    public void setWatergatename(String watergatename) {
        this.watergatename = watergatename;
    }

    public String getBuild_time() {
        return build_time;
    }

    public void setBuild_time(String build_time) {
        this.build_time = build_time;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean existNull(){
        if(watergateid == null || watergatename == null || build_time == null || principal == null || longitude == null || latitude == null || introduction==null){
            return true;
        }
        return false;
    }
}
