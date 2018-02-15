import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { ConsumerProfileSuchazPopupService } from './consumer-profile-suchaz-popup.service';
import { ConsumerProfileSuchazService } from './consumer-profile-suchaz.service';

@Component({
    selector: 'jhi-consumer-profile-suchaz-delete-dialog',
    templateUrl: './consumer-profile-suchaz-delete-dialog.component.html'
})
export class ConsumerProfileSuchazDeleteDialogComponent {

    consumerProfile: ConsumerProfileSuchaz;

    constructor(
        private consumerProfileService: ConsumerProfileSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consumerProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'consumerProfileListModification',
                content: 'Deleted an consumerProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-consumer-profile-suchaz-delete-popup',
    template: ''
})
export class ConsumerProfileSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consumerProfilePopupService: ConsumerProfileSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.consumerProfilePopupService
                .open(ConsumerProfileSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
