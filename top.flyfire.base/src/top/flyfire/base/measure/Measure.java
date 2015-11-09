package top.flyfire.base.measure;

public final class Measure  {
	public enum Time {
		;private String CN_KEY;
		private String EN_KEY;
		private Integer UP_CONV;
		private Integer DOWN_CONV;
		private Time UP_LEVEL;
		private Time DOWN_LEVEL;
		private Time(String cn,String en,Integer up,Integer down,Time upl,Time upd){
			this.setCN_KEY(cn);
			this.setEN_KEY(en);
			this.setUP_CONV(up);
			this.setDOWN_CONV(down);
			this.setUP_LEVEL(upl);
		}
		public String getCN_KEY() {
			return CN_KEY;
		}
		private void setCN_KEY(String cN_KEY) {
			CN_KEY = cN_KEY;
		}
		public String getEN_KEY() {
			return EN_KEY;
		}
		private void setEN_KEY(String eN_KEY) {
			EN_KEY = eN_KEY;
		}
		public Integer getUP_CONV() {
			return UP_CONV;
		}
		private void setUP_CONV(Integer uP_CONV) {
			UP_CONV = uP_CONV;
		}
		public Integer getDOWN_CONV() {
			return DOWN_CONV;
		}
		private void setDOWN_CONV(Integer dOWN_CONV) {
			DOWN_CONV = dOWN_CONV;
		}
		public Time getUP_LEVEL() {
			return UP_LEVEL;
		}
		private void setUP_LEVEL(Time uP_LEVEL) {
			UP_LEVEL = uP_LEVEL;
		}
		public Time getDOWN_LEVEL() {
			return DOWN_LEVEL;
		}
		public void setDOWN_LEVEL(Time dOWN_LEVEL) {
			DOWN_LEVEL = dOWN_LEVEL;
		}
	}
}
