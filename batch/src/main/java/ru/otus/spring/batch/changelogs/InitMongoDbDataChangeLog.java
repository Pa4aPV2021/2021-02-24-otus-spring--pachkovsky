package ru.otus.spring.batch.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;

import ru.otus.spring.batch.mongo.domain.Person;


@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

	@ChangeSet(order = "000", id = "dropDB", author = "PV", runAlways = true)
	public void dropDB(MongoDatabase mongoDatabase) {
		mongoDatabase.drop();
	}

	@ChangeSet(order = "001", id = "initPersons", author = "PV", runAlways = true)
	public void initPersons(MongockTemplate  template) {
		template.save(new Person("Jill", "Doe"));
		template.save(new Person("Joe", "Doe"));
		template.save(new Person("Joe", "Doe"));
	}

}
