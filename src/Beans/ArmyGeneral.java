package Beans;

public class ArmyGeneral extends Soldier{
    private float m_Experience;


    public float getM_Experience() {
        return m_Experience;
    }

    public void setM_Experience(float m_Experience) {
        this.m_Experience = m_Experience;
    }

    public ArmyGeneral(float m_Experience) {
        this.m_Experience = m_Experience;
    }

    public ArmyGeneral(String m_Type, String m_Code, float m_Attack, float m_Defend, boolean m_Equipment, int m_Strength, float m_Experience) {
        super(m_Type, m_Code, m_Attack, m_Defend, m_Equipment, m_Strength);
        this.m_Experience = m_Experience;
    }
}
