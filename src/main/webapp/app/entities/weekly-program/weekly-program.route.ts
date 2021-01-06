import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWeeklyProgram, WeeklyProgram } from 'app/shared/model/weekly-program.model';
import { WeeklyProgramService } from './weekly-program.service';
import { WeeklyProgramComponent } from './weekly-program.component';
import { WeeklyProgramDetailComponent } from './weekly-program-detail.component';
import { WeeklyProgramUpdateComponent } from './weekly-program-update.component';

@Injectable({ providedIn: 'root' })
export class WeeklyProgramResolve implements Resolve<IWeeklyProgram> {
  constructor(private service: WeeklyProgramService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWeeklyProgram> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((weeklyProgram: HttpResponse<WeeklyProgram>) => {
          if (weeklyProgram.body) {
            return of(weeklyProgram.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WeeklyProgram());
  }
}

export const weeklyProgramRoute: Routes = [
  {
    path: '',
    component: WeeklyProgramComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.weeklyProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WeeklyProgramDetailComponent,
    resolve: {
      weeklyProgram: WeeklyProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.weeklyProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WeeklyProgramUpdateComponent,
    resolve: {
      weeklyProgram: WeeklyProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.weeklyProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WeeklyProgramUpdateComponent,
    resolve: {
      weeklyProgram: WeeklyProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.weeklyProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
