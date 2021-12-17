package in.ankushs.dbip.model;

import in.ankushs.dbip.api.GeoEntity;

import java.net.InetAddress;

public final class GeoAttributesImpl implements GeoAttributes {

	private final String city;
	private final String country;
	private final String province ;
	private final String countryCode;
	private final String isp;
	private final InetAddress startInetAddress;
	private final InetAddress endInetAddress;
	private final String continentName;
	private final String stateProvCode;
	private final String asNumber;
	private final String linkType;

	
	private GeoAttributesImpl(final Builder builder){
		this.startInetAddress = builder.startInetAddress;
		this.endInetAddress = builder.endInetAddress;
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
		private  InetAddress startInetAddress;
		private  InetAddress endInetAddress;
		private  String city;
		private  String country;
		private  String province ;
		private  String countryCode;
		private String isp;
		private String continentName;
		private String stateProvCode;
		private String asNumber;
		private String linkType;


		public Builder withIsp(final String isp){
			this.isp = isp;
			return this;
		}
		public Builder withStartInetAddress(final InetAddress startInetAddress){
			this.startInetAddress = startInetAddress;
			return this;
		}

		public Builder withCountryCode(final String countryCode){
			this.countryCode = countryCode;
			return this;
		}
		public Builder withEndInetAddress(final InetAddress endInetAddress){
			this.endInetAddress = endInetAddress;
			return this;
		}


		public Builder withCity(final String city){
			this.city = city;
			return this;
		}
		
		
		public Builder withCountry(final String country){
			this.country = country;
			return this;
		}
		
		
		public Builder withProvince(final String province){
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
		
		public GeoAttributesImpl build(){
			return new GeoAttributesImpl(this);
		}
	}



	@Override
	public InetAddress getStartInetAddress() {
		return startInetAddress;
	}

	@Override
	public InetAddress getEndInetAddress() {
		return endInetAddress;
	}

	@Override
	public GeoEntity getGeoEntity() {
		return new GeoEntity.Builder()
						.withCity(city)
						.withCountry(country)
						.withCountryCode(countryCode)
						.withProvince(province)
						.withIsp(isp)
						.withContinentName(continentName)
						.withStateProvCode(stateProvCode)
						.withAsNumber(asNumber)
						.withLinkType(linkType)
						.build();
	}
	
	
}
