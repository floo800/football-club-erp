import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FootballClubErpSharedModule } from 'app/shared/shared.module';
import { ChildrenComponent } from './children.component';
import { ChildrenDetailComponent } from './children-detail.component';
import { ChildrenUpdateComponent } from './children-update.component';
import { ChildrenDeleteDialogComponent } from './children-delete-dialog.component';
import { childrenRoute } from './children.route';

@NgModule({
  imports: [FootballClubErpSharedModule, RouterModule.forChild(childrenRoute)],
  declarations: [ChildrenComponent, ChildrenDetailComponent, ChildrenUpdateComponent, ChildrenDeleteDialogComponent],
  entryComponents: [ChildrenDeleteDialogComponent],
})
export class FootballClubErpChildrenModule {}
