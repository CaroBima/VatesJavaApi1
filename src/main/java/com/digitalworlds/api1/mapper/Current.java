
package com.digitalworlds.api1.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "last_updated_epoch",
    "last_updated",
    "temp_c",
    "temp_f",
    "is_day",
    "condition",
    "wind_mph",
    "wind_kph",
    "wind_degree",
    "wind_dir",
    "pressure_mb",
    "pressure_in",
    "precip_mm",
    "precip_in",
    "humidity",
    "cloud",
    "feelslike_c",
    "feelslike_f",
    "vis_km",
    "vis_miles",
    "uv",
    "gust_mph",
    "gust_kph"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Current {

    @JsonProperty("last_updated_epoch")
    public Integer lastUpdatedEpoch;
    @JsonProperty("last_updated")
    public String lastUpdated;
    @JsonProperty("temp_c")
    public Double tempC;
    @JsonProperty("temp_f")
    public Double tempF;
    @JsonProperty("is_day")
    public Integer isDay;
    @JsonProperty("condition")
    public Condition condition;
    @JsonProperty("wind_mph")
    public Double windMph;
    @JsonProperty("wind_kph")
    public Double windKph;
    @JsonProperty("wind_degree")
    public Integer windDegree;
    @JsonProperty("wind_dir")
    public String windDir;
    @JsonProperty("pressure_mb")
    public Double pressureMb;
    @JsonProperty("pressure_in")
    public Double pressureIn;
    @JsonProperty("precip_mm")
    public Double precipMm;
    @JsonProperty("precip_in")
    public Double precipIn;
    @JsonProperty("humidity")
    public Integer humidity;
    @JsonProperty("cloud")
    public Integer cloud;
    @JsonProperty("feelslike_c")
    public Double feelslikeC;
    @JsonProperty("feelslike_f")
    public Double feelslikeF;
    @JsonProperty("vis_km")
    public Double visKm;
    @JsonProperty("vis_miles")
    public Double visMiles;
    @JsonProperty("uv")
    public Double uv;
    @JsonProperty("gust_mph")
    public Double gustMph;
    @JsonProperty("gust_kph")
    public Double gustKph;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
