import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { ConsumerProfileHistorySuchazPopupService } from './consumer-profile-history-suchaz-popup.service';
import { ConsumerProfileHistorySuchazService } from './consumer-profile-history-suchaz.service';

@Component({
    selector: 'jhi-consumer-profile-history-suchaz-delete-dialog',
    templateUrl: './consumer-profile-history-suchaz-delete-dialog.component.html'
})
export class ConsumerProfileHistorySuchazDeleteDialogComponent {

    consumerProfileHistory: ConsumerProfileHistorySuchaz;

    constructor(
        private consumerProfileHistoryService: ConsumerProfileHistorySuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consumerProfileHistoryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'consumerProfileHistoryListModification',
                content: 'Deleted an consumerProfileHistory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-consumer-profile-history-suchaz-delete-popup',
    template: ''
})
export class ConsumerProfileHistorySuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consumerProfileHistoryPopupService: ConsumerProfileHistorySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.consumerProfileHistoryPopupService
                .open(ConsumerProfileHistorySuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
