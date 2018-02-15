import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { ActivityListItemSuchazPopupService } from './activity-list-item-suchaz-popup.service';
import { ActivityListItemSuchazService } from './activity-list-item-suchaz.service';

@Component({
    selector: 'jhi-activity-list-item-suchaz-delete-dialog',
    templateUrl: './activity-list-item-suchaz-delete-dialog.component.html'
})
export class ActivityListItemSuchazDeleteDialogComponent {

    activityListItem: ActivityListItemSuchaz;

    constructor(
        private activityListItemService: ActivityListItemSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.activityListItemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'activityListItemListModification',
                content: 'Deleted an activityListItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-activity-list-item-suchaz-delete-popup',
    template: ''
})
export class ActivityListItemSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private activityListItemPopupService: ActivityListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.activityListItemPopupService
                .open(ActivityListItemSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
