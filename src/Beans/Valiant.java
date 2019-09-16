package Beans;

public class Valiant extends Soldier {
    public Valiant() {
    }

    public Valiant(String m_Type, String m_Code, float m_Attack, float m_Defend, boolean m_Equipment, int m_Strength) {
        super(m_Type, m_Code, m_Attack, m_Defend, m_Equipment, m_Strength);
        if(m_Equipment) this.setM_Defend(m_Defend*2);
    }
}
