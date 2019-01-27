package com.ump.core.base.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String>chunk(1).reader(new Reader())
				.processor(new Processor()).writer(new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType("oracle");
		return jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
		return jobLauncher;
	}

	@Bean
	public Job importJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importJob").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
			ItemProcessor<Person, Person> processor) {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(65000).reader(reader).processor(processor)
				.writer(writer).build();
	}

	@Bean
	public ItemReader<Person> reader() throws Exception {
		// 新建ItemReader接口的实现类返回
		return reader;
	}

	@Bean
	public ItemProcessor<Person, Person> processor() {
		// 新建ItemProcessor接口的实现类返回
		return processor;
	}

	@Bean
	public ItemWriter<Person> writer(DataSource dataSource) {
		// 新建ItemWriter接口的实现类返回
		return writer;
	}


	@Bean
	public MyJobListener myJobListener() {
		return new MyJobListener();
	}
//	@StepScope
//	public JpaPagingItemReader<TestReport> reader() {
//		JpaPagingItemReader<TestReport> reader = new JpaPagingItemReader<TestReport>();
//		reader.setQueryString("selectnew TestReport(ttp.taskId, tra.fileId, ttp.ruleId,sum( tra.count))"
//				+ " fromTestFile tra,TestTaskProperty ttp WHERE ttp.taskId=tra.taskId AND ttp.beginTimeBETWEEN ?1 AND ?2 "
//				+ "GROUP BYttp.taskId, tra.fileId, ttp.ruleId");
//		Map<String, Object> parameterValues = new HashMap<>();
//		parameterValues.put("1", CommonUtils.getTimeSection(0, 0, 0));
//		parameterValues.put("2", CommonUtils.getTimeSection(23, 59, 59));
//		reader.setParameterValues(parameterValues);
//		reader.setEntityManagerFactory(emf);
//		reader.setPageSize(Integer.MAX_VALUE);
//		return reader;
//	}
//
//	@Bean
//	public TestFileProcessor processor() {
//		return new TestFileProcessor();
//	}

//	@Bean
//	public JpaItemWriter<TestReport> writer() {
//		JpaItemWriter<TestReport> writer = new JpaItemWriter<TestReport>();
//		writer.setEntityManagerFactory(emf);
//		return writer;
//	}
//
//	@Bean
//	public Step step() {
//		return stepBuilderFactory.get("step").<TestReport, TestReport>chunk(10).reader(reader()).processor(processor())
//				.writer(writer()).build();
//	}
//
//	@Bean
//	public Job importUserJob(JobRepository jobRepository) {
//		return jobBuilderFactory.get("importUserJob").incrementer(newRunIdIncrementer()).repository(jobRepository)
//				.flow(step()).end().build();
//	}
}
