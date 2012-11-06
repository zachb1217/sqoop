/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.framework;

import org.apache.sqoop.common.ImmutableContext;
import org.apache.sqoop.connector.spi.SqoopConnector;
import org.apache.sqoop.model.MSubmission;

/**
 * Execution engine drive execution of sqoop submission (job). It's responsible
 * for executing all defined steps in the import/export workflow.
 */
public abstract class ExecutionEngine {

  /**
   * Initialize execution engine
   *
   * @param context Configuration context
   */
  public void initialize(ImmutableContext context, String prefix) {
  }

  /**
   * Destroy execution engine when stopping server
   */
  public void destroy() {
  }

  /**
   * Return new SubmissionRequest class or any subclass if it's needed by
   * execution and submission engine combination.
   *
   * @param summary Submission summary
   * @param connector Appropriate connector structure
   * @param connectorConnection Connector connection configuration
   * @param connectorJob Connector job configuration
   * @param frameworkConnection Framework connection configuration
   * @param frameworkJob Framework job configuration
   * @return New Submission request object
   */
  public SubmissionRequest createSubmissionRequest(MSubmission summary,
                                                   SqoopConnector connector,
                                                   Object connectorConnection,
                                                   Object connectorJob,
                                                   Object frameworkConnection,
                                                   Object frameworkJob) {
    return new SubmissionRequest(summary, connector,
      connectorConnection, connectorJob, frameworkConnection, frameworkJob);
  }

  /**
   * Prepare given submission request for import submission.
   *
   * @param request Submission request
   */
  public abstract void prepareImportSubmission(SubmissionRequest request);
}
