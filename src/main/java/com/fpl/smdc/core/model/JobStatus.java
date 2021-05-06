
package com.fpl.smdc.core.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class JobStatus.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "intf_job_stat_id", "intf_config_id", "job_name", "process_flag",
    "process_start_time", "process_end_time", "next_process_start_time", "crte_by", "crte_dttm",
    "updt_by", "updt_dttm" })

public class JobStatus {

  /** The intf job stat id. */
  @JsonProperty("intf_job_stat_id")

  private Integer intfJobStatId;

  /** The intf config id. */
  @JsonProperty("intf_config_id")

  private Integer intfConfigId;

  /** The job name. */
  @JsonProperty("job_name")

  private String jobName;

  /** The process flag. */
  @JsonProperty("process_flag")

  private String processFlag;

  /** The process start time. */
  @JsonProperty("process_start_time")
  private Timestamp processStartTime;

  /** The process end time. */
  @JsonProperty("process_end_time")
  private Timestamp processEndTime;

  /** The next process start time. */
  @JsonProperty("next_process_start_time")
  private Timestamp nextProcessStartTime;

  /** The crte by. */
  @JsonProperty("crte_by")
  private String crteBy;

  /** The crte dttm. */
  @JsonProperty("crte_dttm")
  private Timestamp crteDttm;

  /** The updt by. */
  @JsonProperty("updt_by")

  private String updtBy;

  /** The updt dttm. */
  @JsonProperty("updt_dttm")
  private Timestamp updtDttm;

  /**
   * Gets the crte by.
   *
   * @return the crte by
   */
  @JsonProperty("crte_by")
  public String getCrteBy() {
    return crteBy;
  }

  /**
   * Gets the crte dttm.
   *
   * @return the crte dttm
   */
  @JsonProperty("crte_dttm")
  public Timestamp getCrteDttm() {
    return crteDttm;
  }

  /**
   * Gets the intf config id.
   *
   * @return the intf config id
   */
  @JsonProperty("intf_config_id")
  public Integer getIntfConfigId() {
    return intfConfigId;
  }

  /**
   * Gets the intf job stat id.
   *
   * @return the intf job stat id
   */
  @JsonProperty("intf_job_stat_id")
  public Integer getIntfJobStatId() {
    return intfJobStatId;
  }

  /**
   * Gets the job name.
   *
   * @return the job name
   */
  @JsonProperty("job_name")
  public String getJobName() {
    return jobName;
  }

  /**
   * Gets the next process start time.
   *
   * @return the next process start time
   */
  @JsonProperty("next_process_start_time")
  public Timestamp getNextProcessStartTime() {
    return nextProcessStartTime;
  }

  /**
   * Gets the process end time.
   *
   * @return the process end time
   */
  @JsonProperty("process_end_time")
  public Timestamp getProcessEndTime() {
    return processEndTime;
  }

  /**
   * Gets the process flag.
   *
   * @return the process flag
   */
  @JsonProperty("process_flag")
  public String getProcessFlag() {
    return processFlag;
  }

  /**
   * Gets the process start time.
   *
   * @return the process start time
   */
  @JsonProperty("process_start_time")
  public Timestamp getProcessStartTime() {
    return processStartTime;
  }

  /**
   * Gets the updt by.
   *
   * @return the updt by
   */
  @JsonProperty("updt_by")
  public String getUpdtBy() {
    return updtBy;
  }

  /**
   * Gets the updt dttm.
   *
   * @return the updt dttm
   */
  @JsonProperty("updt_dttm")
  public Timestamp getUpdtDttm() {
    return updtDttm;
  }

  /**
   * Sets the crte by.
   *
   * @param crteBy the new crte by
   */
  @JsonProperty("crte_by")
  public void setCrteBy(String crteBy) {
    this.crteBy = crteBy;
  }

  /**
   * Sets the crte dttm.
   *
   * @param crteDttm the new crte dttm
   */
  @JsonProperty("crte_dttm")
  public void setCrteDttm(Timestamp crteDttm) {
    this.crteDttm = crteDttm;
  }

  /**
   * Sets the intf config id.
   *
   * @param intfConfigId the new intf config id
   */
  @JsonProperty("intf_config_id")
  public void setIntfConfigId(Integer intfConfigId) {
    this.intfConfigId = intfConfigId;
  }

  /**
   * Sets the intf job stat id.
   *
   * @param intfJobStatId the new intf job stat id
   */
  @JsonProperty("intf_job_stat_id")
  public void setIntfJobStatId(Integer intfJobStatId) {
    this.intfJobStatId = intfJobStatId;
  }

  /**
   * Sets the job name.
   *
   * @param jobName the new job name
   */
  @JsonProperty("job_name")
  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  /**
   * Sets the next process start time.
   *
   * @param nextProcessStartTime the new next process start time
   */
  @JsonProperty("next_process_start_time")
  public void setNextProcessStartTime(Timestamp nextProcessStartTime) {
    this.nextProcessStartTime = nextProcessStartTime;
  }

  /**
   * Sets the process end time.
   *
   * @param processEndTime the new process end time
   */
  @JsonProperty("process_end_time")
  public void setProcessEndTime(Timestamp processEndTime) {
    this.processEndTime = processEndTime;
  }

  /**
   * Sets the process flag.
   *
   * @param processFlag the new process flag
   */
  @JsonProperty("process_flag")
  public void setProcessFlag(String processFlag) {
    this.processFlag = processFlag;
  }

  /**
   * Sets the process start time.
   *
   * @param processStartTime the new process start time
   */
  @JsonProperty("process_start_time")
  public void setProcessStartTime(Timestamp processStartTime) {
    this.processStartTime = processStartTime;
  }

  /**
   * Sets the updt by.
   *
   * @param updtBy the new updt by
   */
  @JsonProperty("updt_by")
  public void setUpdtBy(String updtBy) {
    this.updtBy = updtBy;
  }

  /**
   * Sets the updt dttm.
   *
   * @param updtDttm the new updt dttm
   */
  @JsonProperty("updt_dttm")
  public void setUpdtDttm(Timestamp updtDttm) {
    this.updtDttm = updtDttm;
  }

}
