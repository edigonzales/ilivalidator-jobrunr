package ch.so.agi.ilivalidator;

import javax.sql.DataSource;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");
////        dataSourceBuilder.url("jdbc:postgresql://host.docker.internal:54321/edit");
//        dataSourceBuilder.url("jdbc:postgresql://localhost:54321/edit");
//        dataSourceBuilder.username("admin");
//        dataSourceBuilder.password("admin");
//        return dataSourceBuilder.build();
//    }

    
//    @Autowired
//    DataSource dataSource;
    
//    @Bean
//    public StorageProvider storageProvider(JobMapper jobMapper) {
//        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
//        storageProvider.setJobMapper(jobMapper);
//        return storageProvider;
//    }

}
