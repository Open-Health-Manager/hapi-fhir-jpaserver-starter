/*
Copyright 2022 The MITRE Corporation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.mitre.healthmanager.dataMgr.resourceTypes

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoPatient
import ca.uhn.fhir.model.primitive.IdDt
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.MessageHeader
import org.hl7.fhir.r4.model.Patient
import org.mitre.healthmanager.dataMgr.getUsernameFromPDRHeader
import org.mitre.healthmanager.dataMgr.getUsernameFromPatient
import org.mitre.healthmanager.dataMgr.sharedUsername

// check the subject for a patient reference, and follow it to get the username
fun Condition.findUsernameViaLinkedPatient(patientDao : IFhirResourceDaoPatient<Patient>) : String? {
    return getPatientUsernameFromReference(this.subject, patientDao, "Condition")
}