import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'children',
        loadChildren: () => import('./children/children.module').then(m => m.FootballClubErpChildrenModule),
      },
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.FootballClubErpTeamModule),
      },
      {
        path: 'weekly-program',
        loadChildren: () => import('./weekly-program/weekly-program.module').then(m => m.FootballClubErpWeeklyProgramModule),
      },
      {
        path: 'training',
        loadChildren: () => import('./training/training.module').then(m => m.FootballClubErpTrainingModule),
      },
      {
        path: 'event',
        loadChildren: () => import('./event/event.module').then(m => m.FootballClubErpEventModule),
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.FootballClubErpDocumentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FootballClubErpEntityModule {}
