package it.univpm.TicketMasterUS.Exceptions;

public class ParamException extends Exception {
	
	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
		private static final long serialVersionUID = 1L;

		/**
		 * Costrutture dell'oggetto.
		 */
		public ParamException() {
			super("Il parametro specificato non è valido");
		}

}
