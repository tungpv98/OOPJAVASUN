package Beans;

public class Soldier {
    private String m_Type;
    private String m_Code;
    private float m_Attack;
    private float m_Defend;
    private boolean m_Equipment;
    private int m_Strength;

    public Soldier() {
    }
    public void update(){
        this.m_Attack -=20;
        this.m_Defend -=20;
    }

    public Soldier(String m_Type, String m_Code, float m_Attack, float m_Defend, boolean m_Equipment, int m_Strength) {
        this.m_Type = m_Type;
        this.m_Code = m_Code;
        this.m_Attack = m_Attack;
        this.m_Defend = m_Defend;
        this.m_Equipment = m_Equipment;
        this.m_Strength = m_Strength;
    }

    public String getM_Type() {
        return m_Type;
    }

    public void setM_Type(String m_Type) {
        this.m_Type = m_Type;
    }

    public String getM_Code() {
        return m_Code;
    }

    public void setM_Code(String m_Code) {
        this.m_Code = m_Code;
    }

    public float getM_Attack() {
        return m_Attack;
    }

    public void setM_Attack(float m_Attack) {
        this.m_Attack = m_Attack;
    }

    public float getM_Defend() {
        return m_Defend;
    }

    public void setM_Defend(float m_Defend) {
        this.m_Defend = m_Defend;
    }

    public boolean isM_Equipment() {
        return m_Equipment;
    }

    public void setM_Equipment(boolean m_Equipment) {
        this.m_Equipment = m_Equipment;
    }

    public int getM_Strength() {
        return m_Strength;
    }

    public void setM_Strength(int m_Strength) {
        this.m_Strength = m_Strength;
    }
}
