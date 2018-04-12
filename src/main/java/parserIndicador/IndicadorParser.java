package parserIndicador;
// Generated from Indicador.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IndicadorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, CUENTA=7, INT=8;
	public static final int
		RULE_indicador = 0;
	public static final String[] ruleNames = {
		"indicador"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "CUENTA", "INT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Indicador.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IndicadorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class IndicadorContext extends ParserRuleContext {
		public IndicadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indicador; }
	 
		public IndicadorContext() { }
		public void copyFrom(IndicadorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NUMEROContext extends IndicadorContext {
		public Token num;
		public TerminalNode INT() { return getToken(IndicadorParser.INT, 0); }
		public NUMEROContext(IndicadorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitNUMERO(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CUENTAContext extends IndicadorContext {
		public Token cuenta;
		public TerminalNode CUENTA() { return getToken(IndicadorParser.CUENTA, 0); }
		public CUENTAContext(IndicadorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitCUENTA(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OperacionContext extends IndicadorContext {
		public IndicadorContext left;
		public Token op;
		public IndicadorContext right;
		public List<IndicadorContext> indicador() {
			return getRuleContexts(IndicadorContext.class);
		}
		public IndicadorContext indicador(int i) {
			return getRuleContext(IndicadorContext.class,i);
		}
		public OperacionContext(IndicadorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitOperacion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class INDContext extends IndicadorContext {
		public IndicadorContext indicador() {
			return getRuleContext(IndicadorContext.class,0);
		}
		public INDContext(IndicadorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitIND(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndicadorContext indicador() throws RecognitionException {
		return indicador(0);
	}

	private IndicadorContext indicador(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IndicadorContext _localctx = new IndicadorContext(_ctx, _parentState);
		IndicadorContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_indicador, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new INDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(3);
				match(T__0);
				setState(4);
				indicador(0);
				setState(5);
				match(T__1);
				}
				break;
			case INT:
				{
				_localctx = new NUMEROContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(7);
				((NUMEROContext)_localctx).num = match(INT);
				}
				break;
			case CUENTA:
				{
				_localctx = new CUENTAContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(8);
				((CUENTAContext)_localctx).cuenta = match(CUENTA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(19);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(17);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new OperacionContext(new IndicadorContext(_parentctx, _parentState));
						((OperacionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_indicador);
						setState(11);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(12);
						((OperacionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__3) ) {
							((OperacionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(13);
						((OperacionContext)_localctx).right = indicador(5);
						}
						break;
					case 2:
						{
						_localctx = new OperacionContext(new IndicadorContext(_parentctx, _parentState));
						((OperacionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_indicador);
						setState(14);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(15);
						((OperacionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5) ) {
							((OperacionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(16);
						((OperacionContext)_localctx).right = indicador(4);
						}
						break;
					}
					} 
				}
				setState(21);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return indicador_sempred((IndicadorContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean indicador_sempred(IndicadorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n\31\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\5\2\f\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\24\n\2"+
		"\f\2\16\2\27\13\2\3\2\2\3\2\3\2\2\4\3\2\5\6\3\2\7\b\2\33\2\13\3\2\2\2"+
		"\4\5\b\2\1\2\5\6\7\3\2\2\6\7\5\2\2\2\7\b\7\4\2\2\b\f\3\2\2\2\t\f\7\n\2"+
		"\2\n\f\7\t\2\2\13\4\3\2\2\2\13\t\3\2\2\2\13\n\3\2\2\2\f\25\3\2\2\2\r\16"+
		"\f\6\2\2\16\17\t\2\2\2\17\24\5\2\2\7\20\21\f\5\2\2\21\22\t\3\2\2\22\24"+
		"\5\2\2\6\23\r\3\2\2\2\23\20\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26"+
		"\3\2\2\2\26\3\3\2\2\2\27\25\3\2\2\2\5\13\23\25";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}