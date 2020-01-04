--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8
-- Dumped by pg_dump version 10.8

-- Started on 2019-06-15 19:02:16
\c fundraiser;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2846 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 49161)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    category_id integer NOT NULL,
    category_name character varying(200) NOT NULL,
    image_name character varying(100) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 49164)
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_category_id_seq OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 197
-- Name: category_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_category_id_seq OWNED BY public.category.category_id;


--
-- TOC entry 198 (class 1259 OID 49166)
-- Name: cause; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cause (
    cause_id integer NOT NULL,
    accountnumber character varying(20) NOT NULL,
    bic character varying(20) NOT NULL,
    category_id integer NOT NULL,
    cause_expiration_date date NOT NULL,
    cause_target_amt integer NOT NULL,
    cause_title character varying(500) NOT NULL,
    collected_amt integer NOT NULL,
    created_by integer,
    creation_date timestamp without time zone NOT NULL,
    description character varying(5000),
    image_name character varying(255) NOT NULL,
    is_active boolean NOT NULL
);


ALTER TABLE public.cause OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 49172)
-- Name: cause_cause_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cause_cause_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cause_cause_id_seq OWNER TO postgres;

--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 199
-- Name: cause_cause_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cause_cause_id_seq OWNED BY public.cause.cause_id;


--
-- TOC entry 200 (class 1259 OID 49174)
-- Name: donation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.donation (
    donation_id integer NOT NULL,
    amount_donated integer NOT NULL,
    cause_id integer NOT NULL,
    donated_by integer NOT NULL
);


ALTER TABLE public.donation OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 49177)
-- Name: donation_donation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.donation_donation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.donation_donation_id_seq OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 201
-- Name: donation_donation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.donation_donation_id_seq OWNED BY public.donation.donation_id;


--
-- TOC entry 202 (class 1259 OID 49179)
-- Name: login_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login_session (
    access_token character varying(2000) NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.login_session OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 49185)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    user_id integer NOT NULL,
    creation_date timestamp without time zone,
    email_id character varying(255) NOT NULL,
    is_active boolean NOT NULL,
    modified_date timestamp without time zone,
    user_type character varying(255) NOT NULL
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 49191)
-- Name: user_details_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_details_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_details_user_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_details_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_details_user_id_seq OWNED BY public.user_details.user_id;


--
-- TOC entry 2695 (class 2604 OID 49207)
-- Name: category category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN category_id SET DEFAULT nextval('public.category_category_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 49208)
-- Name: cause cause_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cause ALTER COLUMN cause_id SET DEFAULT nextval('public.cause_cause_id_seq'::regclass);


--
-- TOC entry 2697 (class 2604 OID 49209)
-- Name: donation donation_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.donation ALTER COLUMN donation_id SET DEFAULT nextval('public.donation_donation_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 49210)
-- Name: user_details user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details ALTER COLUMN user_id SET DEFAULT nextval('public.user_details_user_id_seq'::regclass);


--
-- TOC entry 2830 (class 0 OID 49161)
-- Dependencies: 196
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (category_id, category_name, image_name) FROM stdin;
1	Natural Disaster	1.png
2	Medical Cause	2.png
3	Education Help	3.png
\.


--
-- TOC entry 2832 (class 0 OID 49166)
-- Dependencies: 198
-- Data for Name: cause; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cause (cause_id, accountnumber, bic, category_id, cause_expiration_date, cause_target_amt, cause_title, collected_amt, created_by, creation_date, description, image_name, is_active) FROM stdin;
3	10897634567876545678	SOLADE12	1	2019-07-17	8000	Fund Raiser for Wildfire Cause in California	0	1	2019-06-13 03:45:47.991	The Camp Fire ravaged North California, destroying more than 18,000 structures. The Camp Fire was the worst fire in California to this date, and was fueled by large national forests.	1.png	t
2	10654367902345678987	SOLADE45	1	2019-06-27	600	Raise Fund for Cyclone Fani	200	1	2019-06-13 03:27:40.958	This cause is created in-order to help the people who are suffering from Cyclone Fani. Cyclone Fani has further intensified into an “severe cyclonic storm” and is approaching the Odisha coast. A red alert warning for 17 districts of Odisha has been issued on 30 April.	1.png	t
1	10578908780757830063	SOLADE23	3	2019-08-08	10000	Cause for helping the Under Privileged Children for Education	700	1	2019-06-13 03:20:00.206	This cause is created in-order to help children's which are not able to gain education due to their financial condition. With this cause your amount donated will be used to provide basic amenities for children such as Books, Pencils, Bags and also they will be able to go to school.	3.png	t
5	DE76552545961420326	SOFQRYH	1	2019-10-30	500000	Collection for Kerla cyclone	0	1	2019-06-13 10:31:52.665	Recently there was a huge cyclone which distroyed most of the area of Kerla,India. many people lost their houses and business. We are collecting money for such people to help rebuild their losses and bring their life to normal. Your small donation amount will lead to help cyclone impacted people. Thank you!	1.png	t
\.


--
-- TOC entry 2834 (class 0 OID 49174)
-- Dependencies: 200
-- Data for Name: donation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.donation (donation_id, amount_donated, cause_id, donated_by) FROM stdin;
1	50	2	1
2	300	2	1
3	250	2	1
4	200	2	1
5	100	1	1
6	10	8	1
\.


--
-- TOC entry 2836 (class 0 OID 49179)
-- Dependencies: 202
-- Data for Name: login_session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.login_session (access_token, user_id) FROM stdin;
eyJraWQiOiIyWTF5ZWMzTUwzVE83RktRbHptRndIV1VQN0pVdzE1UmVrc3dyQlZibVhFPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2MDJmNWEzNy05NTU1LTQwZTgtOThlZC03MWRmOGEyNmIzM2UiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LWNlbnRyYWwtMS5hbWF6b25hd3MuY29tXC9ldS1jZW50cmFsLTFfbndkQ0JzUXoxIiwicGhvbmVfbnVtYmVyX3ZlcmlmaWVkIjpmYWxzZSwiY29nbml0bzp1c2VybmFtZSI6IjYwMmY1YTM3LTk1NTUtNDBlOC05OGVkLTcxZGY4YTI2YjMzZSIsImF1ZCI6IjIwYzFwN283dWhsMDBhdHYyYnV2dDY5b2xnIiwiZXZlbnRfaWQiOiI2MmYwYTdhNS04M2ViLTQxOTAtODA0Ny0yMmJmMWU5ODZlZmYiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU2MDYxNjI1NywicGhvbmVfbnVtYmVyIjoiKzExMjMxMjMxMjMiLCJleHAiOjE1NjA2MTk4NTgsImlhdCI6MTU2MDYxNjI1OCwiZW1haWwiOiJtY2JjODM3NDhAZ21haWwuY29tIn0.lN8XhOP1v7vW69EaL7-2miDvHacrhRys56ISUtxTNcYNHijfbmJ5LjozE9EWeYfgOPsIXgszCJhb93c0o0OKjM-TfMTrUYJVFp3MVpIV0oQeHozM-gnHwnLZjPImwgBeQQxBbIHa5KkdKpquAbbkQVfnBTr-KI5mi3i7TPjsXMBGYVuSpvk2u7llBRmYVfxjtkufmgGcRQzVqOFJc3EN_OhJjY4mmrn0TDt4cIaIfKHJzXJUnm64QL7MAw33kOmJGvyUjzag1To2ijxBS7ESvjdtL1pL1ozU1ZkK4UTcGXy13TfybG9JHPBk1Pk5B-1CXwHXey1loUuCanGwwej75Q	1
\.


--
-- TOC entry 2837 (class 0 OID 49185)
-- Dependencies: 203
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_details (user_id, creation_date, email_id, is_active, modified_date, user_type) FROM stdin;
1	2019-06-12 20:36:15.181	raiserfund71@gmail.com	t	2019-06-12 20:36:15.181	admin
\.


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 197
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_category_id_seq', 3, true);


--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 199
-- Name: cause_cause_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cause_cause_id_seq', 8, true);


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 201
-- Name: donation_donation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.donation_donation_id_seq', 6, true);


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_details_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_details_user_id_seq', 4, true);


--
-- TOC entry 2700 (class 2606 OID 49198)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- TOC entry 2702 (class 2606 OID 49200)
-- Name: cause cause_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cause
    ADD CONSTRAINT cause_pkey PRIMARY KEY (cause_id);


--
-- TOC entry 2704 (class 2606 OID 49202)
-- Name: donation donation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.donation
    ADD CONSTRAINT donation_pkey PRIMARY KEY (donation_id);


--
-- TOC entry 2706 (class 2606 OID 49204)
-- Name: login_session login_session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_session
    ADD CONSTRAINT login_session_pkey PRIMARY KEY (access_token);


--
-- TOC entry 2708 (class 2606 OID 49206)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


-- Completed on 2019-06-15 19:02:16

--
-- PostgreSQL database dump complete
--

