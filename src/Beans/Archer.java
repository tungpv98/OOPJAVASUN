package Beans;

public class Archer extends Soldier {
    public Archer() {
    }

    public Archer(String m_Type, String m_Code, float m_Attack, float m_Defend, boolean m_Equipment, int m_Strength) {
        super(m_Type, m_Code, m_Attack, m_Defend, m_Equipment, m_Strength);
        if(m_Equipment){
            this.setM_Attack((float) (m_Attack*1.5));
        }
    }
}
