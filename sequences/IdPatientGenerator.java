package com.integrys.backend.sequences;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

public class IdPatientGenerator  extends SequenceStyleGenerator {
	
	     
	    public static final String FIX_FORMAT_PARAMETER = "fixFormat";
	    public static final String FIX_FORMAT_DEFAULT = "PA";
	     
	    public static final String INCREMENT_SUFFIX_PARAMETER = "incrementSuffix";
	    public static final String INCREMENT_SUFFIX_DEFAULT = "%05d";
	     
	    private String format;
	     
	    @Override
	    public Serializable generate(SharedSessionContractImplementor session,
	            Object object) throws HibernateException {
	        return String.format(format, LocalDate.now(), super.generate(session, object));
	    }
	 
	    @Override
	    public void configure(Type type, Properties params,
	            ServiceRegistry serviceRegistry) throws MappingException {
	        super.configure(LongType.INSTANCE, params, serviceRegistry);
	 
//	        String dateFormat = ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT).replace("%", "%1"); 
	        String incrementSuffix = ConfigurationHelper.getString(INCREMENT_SUFFIX_PARAMETER, params, INCREMENT_SUFFIX_DEFAULT).replace("%", "%2"); 
	        String fixFormat = ConfigurationHelper.getString(FIX_FORMAT_PARAMETER, params, FIX_FORMAT_DEFAULT); 
	        this.format = (Calendar.getInstance().get(Calendar.YEAR) % 100) +fixFormat+incrementSuffix; 
	        System.out.println("le format: "+ this.format);
	    }



}
