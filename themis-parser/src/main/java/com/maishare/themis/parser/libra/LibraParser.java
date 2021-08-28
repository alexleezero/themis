// Generated from D:/workspaces/m-bravo/themis/themis/themis-parser/src/main/java/com/maishare/themis/parser/libra\Libra.g4 by ANTLR 4.8
package com.maishare.themis.parser.libra;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LibraParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		WS=18, EQUAL=19, DOLLAR=20, LIBRA=21, LINE_FEED=22, TRUE=23, FALSE=24, 
		ID=25, LETTER=26, DIGIT=27, COLON=28, STRING=29, ESC=30, NIL=31;
	public static final int
		RULE_file = 0, RULE_pair = 1, RULE_value = 2, RULE_stat = 3, RULE_varDecl = 4, 
		RULE_superCompDecl = 5, RULE_compDecl = 6, RULE_compType = 7, RULE_compName = 8, 
		RULE_params = 9, RULE_conditionDecl = 10, RULE_realBlock = 11, RULE_fakeBlock = 12, 
		RULE_conditionExpr = 13, RULE_followedCondition = 14, RULE_contrastCondExpr = 15, 
		RULE_conditionOps = 16, RULE_expr = 17, RULE_externalVarName = 18, RULE_digits = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "pair", "value", "stat", "varDecl", "superCompDecl", "compDecl", 
			"compType", "compName", "params", "conditionDecl", "realBlock", "fakeBlock", 
			"conditionExpr", "followedCondition", "contrastCondExpr", "conditionOps", 
			"expr", "externalVarName", "digits"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'@'", "'('", "','", "')'", "'if'", "'else'", "'end'", 
			"'&&'", "'||'", "'=='", "'!='", "'*'", "'/'", "'+'", "'-'", null, "'='", 
			"'$'", "'libra'", "';'", "'true'", "'false'", null, null, null, "':'", 
			null, null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "WS", "EQUAL", "DOLLAR", "LIBRA", 
			"LINE_FEED", "TRUE", "FALSE", "ID", "LETTER", "DIGIT", "COLON", "STRING", 
			"ESC", "NIL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "Libra.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LibraParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(40);
				pair();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public TerminalNode COLON() { return getToken(LibraParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(ID);
			setState(47);
			match(COLON);
			setState(48);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode STRING() { return getToken(LibraParser.STRING, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value);
		int _la;
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(50);
				match(T__0);
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << LIBRA) | (1L << ID))) != 0)) {
					{
					{
					setState(51);
					stat();
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(57);
				match(T__1);
				}
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public SuperCompDeclContext superCompDecl() {
			return getRuleContext(SuperCompDeclContext.class,0);
		}
		public CompDeclContext compDecl() {
			return getRuleContext(CompDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ConditionDeclContext conditionDecl() {
			return getRuleContext(ConditionDeclContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stat);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				superCompDecl();
				}
				break;
			case LIBRA:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				compDecl();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				varDecl();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				conditionDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public TerminalNode EQUAL() { return getToken(LibraParser.EQUAL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LINE_FEED() { return getToken(LibraParser.LINE_FEED, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(ID);
			setState(68);
			match(EQUAL);
			setState(69);
			expr(0);
			setState(70);
			match(LINE_FEED);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperCompDeclContext extends ParserRuleContext {
		public CompDeclContext compDecl() {
			return getRuleContext(CompDeclContext.class,0);
		}
		public SuperCompDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superCompDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterSuperCompDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitSuperCompDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitSuperCompDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperCompDeclContext superCompDecl() throws RecognitionException {
		SuperCompDeclContext _localctx = new SuperCompDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_superCompDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__2);
			setState(73);
			compDecl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompDeclContext extends ParserRuleContext {
		public TerminalNode LIBRA() { return getToken(LibraParser.LIBRA, 0); }
		public List<TerminalNode> COLON() { return getTokens(LibraParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(LibraParser.COLON, i);
		}
		public CompTypeContext compType() {
			return getRuleContext(CompTypeContext.class,0);
		}
		public CompNameContext compName() {
			return getRuleContext(CompNameContext.class,0);
		}
		public TerminalNode LINE_FEED() { return getToken(LibraParser.LINE_FEED, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public CompDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterCompDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitCompDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitCompDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompDeclContext compDecl() throws RecognitionException {
		CompDeclContext _localctx = new CompDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(LIBRA);
			setState(76);
			match(COLON);
			setState(77);
			compType();
			setState(78);
			match(COLON);
			setState(79);
			compName();
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(80);
				params();
				}
			}

			setState(83);
			match(LINE_FEED);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompTypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public CompTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterCompType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitCompType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitCompType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompTypeContext compType() throws RecognitionException {
		CompTypeContext _localctx = new CompTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_compType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public CompNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterCompName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitCompName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitCompName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompNameContext compName() throws RecognitionException {
		CompNameContext _localctx = new CompNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_compName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__3);
			{
			setState(90);
			expr(0);
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(91);
				match(T__4);
				{
				setState(92);
				expr(0);
				}
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionDeclContext extends ParserRuleContext {
		public ConditionExprContext conditionExpr() {
			return getRuleContext(ConditionExprContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(LibraParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(LibraParser.COLON, i);
		}
		public List<RealBlockContext> realBlock() {
			return getRuleContexts(RealBlockContext.class);
		}
		public RealBlockContext realBlock(int i) {
			return getRuleContext(RealBlockContext.class,i);
		}
		public List<FakeBlockContext> fakeBlock() {
			return getRuleContexts(FakeBlockContext.class);
		}
		public FakeBlockContext fakeBlock(int i) {
			return getRuleContext(FakeBlockContext.class,i);
		}
		public ConditionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterConditionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitConditionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitConditionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionDeclContext conditionDecl() throws RecognitionException {
		ConditionDeclContext _localctx = new ConditionDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conditionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__6);
			setState(101);
			conditionExpr();
			setState(102);
			match(COLON);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << LIBRA) | (1L << ID))) != 0)) {
				{
				{
				setState(103);
				realBlock();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(109);
				match(T__7);
				setState(110);
				match(COLON);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << LIBRA) | (1L << ID))) != 0)) {
					{
					{
					setState(111);
					fakeBlock();
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(119);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealBlockContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public CompDeclContext compDecl() {
			return getRuleContext(CompDeclContext.class,0);
		}
		public SuperCompDeclContext superCompDecl() {
			return getRuleContext(SuperCompDeclContext.class,0);
		}
		public RealBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterRealBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitRealBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitRealBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RealBlockContext realBlock() throws RecognitionException {
		RealBlockContext _localctx = new RealBlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_realBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(121);
				varDecl();
				}
				break;
			case LIBRA:
				{
				setState(122);
				compDecl();
				}
				break;
			case T__2:
				{
				setState(123);
				superCompDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FakeBlockContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public CompDeclContext compDecl() {
			return getRuleContext(CompDeclContext.class,0);
		}
		public SuperCompDeclContext superCompDecl() {
			return getRuleContext(SuperCompDeclContext.class,0);
		}
		public FakeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fakeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterFakeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitFakeBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitFakeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FakeBlockContext fakeBlock() throws RecognitionException {
		FakeBlockContext _localctx = new FakeBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fakeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(126);
				varDecl();
				}
				break;
			case LIBRA:
				{
				setState(127);
				compDecl();
				}
				break;
			case T__2:
				{
				setState(128);
				superCompDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionExprContext extends ParserRuleContext {
		public ContrastCondExprContext contrastCondExpr() {
			return getRuleContext(ContrastCondExprContext.class,0);
		}
		public List<FollowedConditionContext> followedCondition() {
			return getRuleContexts(FollowedConditionContext.class);
		}
		public FollowedConditionContext followedCondition(int i) {
			return getRuleContext(FollowedConditionContext.class,i);
		}
		public ConditionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterConditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitConditionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitConditionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionExprContext conditionExpr() throws RecognitionException {
		ConditionExprContext _localctx = new ConditionExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conditionExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			contrastCondExpr();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) {
				{
				{
				setState(132);
				followedCondition();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FollowedConditionContext extends ParserRuleContext {
		public ConditionOpsContext conditionOps() {
			return getRuleContext(ConditionOpsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FollowedConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_followedCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterFollowedCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitFollowedCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitFollowedCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FollowedConditionContext followedCondition() throws RecognitionException {
		FollowedConditionContext _localctx = new FollowedConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_followedCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			conditionOps();
			setState(139);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContrastCondExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ConditionOpsContext conditionOps() {
			return getRuleContext(ConditionOpsContext.class,0);
		}
		public ContrastCondExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contrastCondExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterContrastCondExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitContrastCondExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitContrastCondExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContrastCondExprContext contrastCondExpr() throws RecognitionException {
		ContrastCondExprContext _localctx = new ContrastCondExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_contrastCondExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			expr(0);
			setState(142);
			conditionOps();
			setState(143);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionOpsContext extends ParserRuleContext {
		public ConditionOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionOps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterConditionOps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitConditionOps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitConditionOps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionOpsContext conditionOps() throws RecognitionException {
		ConditionOpsContext _localctx = new ConditionOpsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_conditionOps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public DigitsContext digits() {
			return getRuleContext(DigitsContext.class,0);
		}
		public ExternalVarNameContext externalVarName() {
			return getRuleContext(ExternalVarNameContext.class,0);
		}
		public TerminalNode STRING() { return getToken(LibraParser.STRING, 0); }
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public TerminalNode TRUE() { return getToken(LibraParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LibraParser.FALSE, 0); }
		public TerminalNode NIL() { return getToken(LibraParser.NIL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(148);
				digits();
				}
				break;
			case 2:
				{
				setState(149);
				externalVarName();
				}
				break;
			case 3:
				{
				setState(150);
				match(STRING);
				}
				break;
			case 4:
				{
				setState(151);
				match(ID);
				}
				break;
			case 5:
				{
				setState(152);
				match(TRUE);
				}
				break;
			case 6:
				{
				setState(153);
				match(FALSE);
				}
				break;
			case 7:
				{
				setState(154);
				match(NIL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(158);
						match(T__13);
						setState(159);
						expr(11);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(161);
						match(T__14);
						setState(162);
						expr(10);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(164);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(165);
						expr(9);
						}
						break;
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	public static class ExternalVarNameContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(LibraParser.DOLLAR, 0); }
		public TerminalNode ID() { return getToken(LibraParser.ID, 0); }
		public ExternalVarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalVarName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterExternalVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitExternalVarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitExternalVarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternalVarNameContext externalVarName() throws RecognitionException {
		ExternalVarNameContext _localctx = new ExternalVarNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_externalVarName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(DOLLAR);
			setState(172);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DigitsContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(LibraParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(LibraParser.DIGIT, i);
		}
		public DigitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).enterDigits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LibraListener ) ((LibraListener)listener).exitDigits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LibraVisitor ) return ((LibraVisitor<? extends T>)visitor).visitDigits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitsContext digits() throws RecognitionException {
		DigitsContext _localctx = new DigitsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_digits);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(174);
					match(DIGIT);
					}
					} 
				}
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00b7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\4\3\4\5\4>\n\4\3\5\3\5\3\5\3"+
		"\5\5\5D\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5"+
		"\bT\n\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\7\13`\n\13\f\13\16"+
		"\13c\13\13\3\13\3\13\3\f\3\f\3\f\3\f\7\fk\n\f\f\f\16\fn\13\f\3\f\3\f\3"+
		"\f\7\fs\n\f\f\f\16\fv\13\f\5\fx\n\f\3\f\3\f\3\r\3\r\3\r\5\r\177\n\r\3"+
		"\16\3\16\3\16\5\16\u0084\n\16\3\17\3\17\7\17\u0088\n\17\f\17\16\17\u008b"+
		"\13\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u009e\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\7\23\u00a9\n\23\f\23\16\23\u00ac\13\23\3\24\3\24\3\24\3\25"+
		"\7\25\u00b2\n\25\f\25\16\25\u00b5\13\25\3\25\2\3$\26\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(\2\4\3\2\f\17\3\2\22\23\2\u00bc\2-\3\2\2\2"+
		"\4\60\3\2\2\2\6=\3\2\2\2\bC\3\2\2\2\nE\3\2\2\2\fJ\3\2\2\2\16M\3\2\2\2"+
		"\20W\3\2\2\2\22Y\3\2\2\2\24[\3\2\2\2\26f\3\2\2\2\30~\3\2\2\2\32\u0083"+
		"\3\2\2\2\34\u0085\3\2\2\2\36\u008c\3\2\2\2 \u008f\3\2\2\2\"\u0093\3\2"+
		"\2\2$\u009d\3\2\2\2&\u00ad\3\2\2\2(\u00b3\3\2\2\2*,\5\4\3\2+*\3\2\2\2"+
		",/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\3\3\2\2\2/-\3\2\2\2\60\61\7\33\2\2\61"+
		"\62\7\36\2\2\62\63\5\6\4\2\63\5\3\2\2\2\648\7\3\2\2\65\67\5\b\5\2\66\65"+
		"\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2;>\7\4\2"+
		"\2<>\7\37\2\2=\64\3\2\2\2=<\3\2\2\2>\7\3\2\2\2?D\5\f\7\2@D\5\16\b\2AD"+
		"\5\n\6\2BD\5\26\f\2C?\3\2\2\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2\2D\t\3\2\2\2"+
		"EF\7\33\2\2FG\7\25\2\2GH\5$\23\2HI\7\30\2\2I\13\3\2\2\2JK\7\5\2\2KL\5"+
		"\16\b\2L\r\3\2\2\2MN\7\27\2\2NO\7\36\2\2OP\5\20\t\2PQ\7\36\2\2QS\5\22"+
		"\n\2RT\5\24\13\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\30\2\2V\17\3\2\2\2"+
		"WX\7\33\2\2X\21\3\2\2\2YZ\7\33\2\2Z\23\3\2\2\2[\\\7\6\2\2\\a\5$\23\2]"+
		"^\7\7\2\2^`\5$\23\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2"+
		"ca\3\2\2\2de\7\b\2\2e\25\3\2\2\2fg\7\t\2\2gh\5\34\17\2hl\7\36\2\2ik\5"+
		"\30\r\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mw\3\2\2\2nl\3\2\2\2op"+
		"\7\n\2\2pt\7\36\2\2qs\5\32\16\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2"+
		"\2ux\3\2\2\2vt\3\2\2\2wo\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7\13\2\2z\27\3"+
		"\2\2\2{\177\5\n\6\2|\177\5\16\b\2}\177\5\f\7\2~{\3\2\2\2~|\3\2\2\2~}\3"+
		"\2\2\2\177\31\3\2\2\2\u0080\u0084\5\n\6\2\u0081\u0084\5\16\b\2\u0082\u0084"+
		"\5\f\7\2\u0083\u0080\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\33\3\2\2\2\u0085\u0089\5 \21\2\u0086\u0088\5\36\20\2\u0087\u0086\3\2"+
		"\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\35\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\5\"\22\2\u008d\u008e\5$\23"+
		"\2\u008e\37\3\2\2\2\u008f\u0090\5$\23\2\u0090\u0091\5\"\22\2\u0091\u0092"+
		"\5$\23\2\u0092!\3\2\2\2\u0093\u0094\t\2\2\2\u0094#\3\2\2\2\u0095\u0096"+
		"\b\23\1\2\u0096\u009e\5(\25\2\u0097\u009e\5&\24\2\u0098\u009e\7\37\2\2"+
		"\u0099\u009e\7\33\2\2\u009a\u009e\7\31\2\2\u009b\u009e\7\32\2\2\u009c"+
		"\u009e\7!\2\2\u009d\u0095\3\2\2\2\u009d\u0097\3\2\2\2\u009d\u0098\3\2"+
		"\2\2\u009d\u0099\3\2\2\2\u009d\u009a\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009c\3\2\2\2\u009e\u00aa\3\2\2\2\u009f\u00a0\f\f\2\2\u00a0\u00a1\7\20"+
		"\2\2\u00a1\u00a9\5$\23\r\u00a2\u00a3\f\13\2\2\u00a3\u00a4\7\21\2\2\u00a4"+
		"\u00a9\5$\23\f\u00a5\u00a6\f\n\2\2\u00a6\u00a7\t\3\2\2\u00a7\u00a9\5$"+
		"\23\13\u00a8\u009f\3\2\2\2\u00a8\u00a2\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a9"+
		"\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab%\3\2\2\2"+
		"\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\26\2\2\u00ae\u00af\7\33\2\2\u00af\'"+
		"\3\2\2\2\u00b0\u00b2\7\35\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2"+
		"\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4)\3\2\2\2\u00b5\u00b3\3"+
		"\2\2\2\22-8=CSaltw~\u0083\u0089\u009d\u00a8\u00aa\u00b3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}