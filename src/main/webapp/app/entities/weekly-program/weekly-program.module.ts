import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FootballClubErpSharedModule } from 'app/shared/shared.module';
import { WeeklyProgramComponent } from './weekly-program.component';
import { WeeklyProgramDetailComponent } from './weekly-program-detail.component';
import { WeeklyProgramUpdateComponent } from './weekly-program-update.component';
import { WeeklyProgramDeleteDialogComponent } from './weekly-program-delete-dialog.component';
import { weeklyProgramRoute } from './weekly-program.route';

@NgModule({
  imports: [FootballClubErpSharedModule, RouterModule.forChild(weeklyProgramRoute)],
  declarations: [WeeklyProgramComponent, WeeklyProgramDetailComponent, WeeklyProgramUpdateComponent, WeeklyProgramDeleteDialogComponent],
  entryComponents: [WeeklyProgramDeleteDialogComponent],
})
export class FootballClubErpWeeklyProgramModule {}
