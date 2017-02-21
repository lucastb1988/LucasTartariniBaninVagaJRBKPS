package br.com.lucas.pharma.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();	

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// Cria uma conexão a partir do hibernate.cfg.xml
			Configuration configuracao = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();

			SessionFactory fabrica = configuracao.buildSessionFactory(registro);

			return fabrica;
			
		} catch (Throwable ex) {
			System.err.println("Fábrica de Sessões não pode ser criada. " + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}	
}