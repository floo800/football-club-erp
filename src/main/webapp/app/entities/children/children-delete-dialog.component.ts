import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChildren } from 'app/shared/model/children.model';
import { ChildrenService } from './children.service';

@Component({
  templateUrl: './children-delete-dialog.component.html',
})
export class ChildrenDeleteDialogComponent {
  children?: IChildren;

  constructor(protected childrenService: ChildrenService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.childrenService.delete(id).subscribe(() => {
      this.eventManager.broadcast('childrenListModification');
      this.activeModal.close();
    });
  }
}
