package com.thedeadpixelsociety.twodee.service

import java.lang.Exception

class MissingServiceException(serviceName: String) : Exception("Missing registered service for type $serviceName.")