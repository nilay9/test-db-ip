package in.ankushs.dbip.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 *
 * @author Ankush Sharma
 */
public final class GeoEntity {

//	city":"Limburg Province","country":"Belgium","province":"Flanders","countryCode":"BE","isp":"Telenet"}"; line
	@JsonProperty("city")
	private  String city;
	@JsonProperty("couty")
	private  String country;

	@JsonProperty("prvnc")
	private  String province;

	@JsonProperty("cCode")
	private  String countryCode;

	@JsonProperty("isp")
	private  String isp;

	@JsonProperty("cName")
	private String continentName;

	@JsonProperty("zCode")
	private String stateProvCode;

	@JsonProperty("aNum")
	private String asNumber;

	@JsonProperty("lType")
	private String linkType;

	public GeoEntity() {}

	public GeoEntity(final Builder builder){
		this.city = builder.city;
		this.country = builder.country;
		this.province = builder.province;
		this.countryCode = builder.countryCode;
		this.isp = builder.isp;
		this.continentName = builder.continentName;
		this.stateProvCode = builder.stateProvCode;
		this.asNumber = builder.asNumber;
		this.linkType = builder.linkType;

	}
	 
	public static class Builder{
		private String countryCode;
		private String city;
		private String country;
		private String province;
		private String isp;
		private String continentName;
		private String stateProvCode;
		private String asNumber;
		private String linkType;


		public Builder withIsp(final String isp){
			this.isp = isp;
			return this;
		}

		public Builder withCountryCode(final String countryCode){
			this.countryCode = countryCode;
			return this;
		}
		
		public Builder withCity(final String city ){
			this.city = city;
			return this;
		}
		
		public Builder withCountry(final String country ){
			this.country = country;
			return this;
		}
		
		public Builder withProvince(final String province ){
			this.province = province;
			return this;
		}

		public Builder withContinentName(final String continentName ){
			this.continentName = continentName;
			return this;
		}

		public Builder withStateProvCode(final String stateProvCode ){
			this.stateProvCode = stateProvCode;
			return this;
		}

		public Builder withAsNumber(final String asNumber ){
			this.asNumber = asNumber;
			return this;
		}

		public Builder withLinkType(final String linkType ){
			this.linkType = linkType;
			return this;
		}
		
		public GeoEntity build(){
			return new GeoEntity(this);
		}
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getIsp() {
		return isp;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getStateProvCode() {
		return stateProvCode;
	}

	public void setStateProvCode(String stateProvCode) {
		this.stateProvCode = stateProvCode;
	}

	public String getAsNumber() {
		return asNumber;
	}

	public void setAsNumber(String asNumber) {
		this.asNumber = asNumber;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	@Override
	public String toString() {
		return "GeoEntity{" +
				"city='" + city + '\'' +
				", country='" + country + '\'' +
				", province='" + province + '\'' +
				", countryCode='" + countryCode + '\'' +
				", isp='" + isp + '\'' +
				", continentName='" + continentName + '\'' +
				", stateProvCode='" + stateProvCode + '\'' +
				", asNumber='" + asNumber + '\'' +
				", linkType='" + linkType + '\'' +
				'}';
	}

	public static void main(String[] args) {

	}
}
