# -*- coding: utf-8 -*-
"""
Created on Thu Jul 30 22:23:40 2020

@author: Manishkumar
"""

from scipy.stats import ttest_ind
import pandas as pd
import numpy as np

df1  = pd.read_csv(r'F:\Dataeaze_test\combined1.csv\part-00000-09c6aad7-714a-4a2a-bd21-ad80a6548000-c000.csv')

df2 = pd.read_csv(r'F:\Dataeaze_test\combined1.csv\part-00001-09c6aad7-714a-4a2a-bd21-ad80a6548000-c000.csv')

df3 = pd.read_csv(r"F:\Dataeaze_test\combined1.csv\part-00002-09c6aad7-714a-4a2a-bd21-ad80a6548000-c000.csv")

df4 = pd.read_csv(r"F:\Dataeaze_test\combined1.csv\part-00003-09c6aad7-714a-4a2a-bd21-ad80a6548000-c000.csv")

df5 = pd.read_csv(r"F:\Dataeaze_test\combined1.csv\part-00004-09c6aad7-714a-4a2a-bd21-ad80a6548000-c000.csv")

df = pd.concat([df1, df2,df3,df4,df5])

df = df.dropna()

df_vir = df[df['State'] == 'VA']

data1 = df_vir[df_vir.LEEDSystemVersionDisplayName=='LEED-NC 2.2']

d1 = data1.PointsAchieved.to_numpy().astype(int)


df_cal = df[df['State'] == 'CA']

data2 = df_cal[df_cal.LEEDSystemVersionDisplayName=='LEED-NC 2.2']

d2 = data2.PointsAchieved.to_numpy().astype(int)

# compare samples
stat, p = ttest_ind(d1, d2,equal_var=False)

print('t=%.3f, p=%.3f' % (stat, p))














