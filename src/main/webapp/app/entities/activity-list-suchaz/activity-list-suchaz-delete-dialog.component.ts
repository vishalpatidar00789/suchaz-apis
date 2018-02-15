import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ActivityListSuchaz } from './activity-list-suchaz.model';
import { ActivityListSuchazPopupService } from './activity-list-suchaz-popup.service';
import { ActivityListSuchazService } from './activity-list-suchaz.service';

@Component({
    selector: 'jhi-activity-list-suchaz-delete-dialog',
    templateUrl: './activity-list-suchaz-delete-dialog.component.html'
})
export class ActivityListSuchazDeleteDialogComponent {

    activityList: ActivityListSuchaz;

    constructor(
        private activityListService: ActivityListSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.activityListService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'activityListListModification',
                content: 'Deleted an activityList'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-activity-list-suchaz-delete-popup',
    template: ''
})
export class ActivityListSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private activityListPopupService: ActivityListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.activityListPopupService
                .open(ActivityListSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
