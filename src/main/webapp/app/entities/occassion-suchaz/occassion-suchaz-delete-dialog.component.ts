import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OccassionSuchaz } from './occassion-suchaz.model';
import { OccassionSuchazPopupService } from './occassion-suchaz-popup.service';
import { OccassionSuchazService } from './occassion-suchaz.service';

@Component({
    selector: 'jhi-occassion-suchaz-delete-dialog',
    templateUrl: './occassion-suchaz-delete-dialog.component.html'
})
export class OccassionSuchazDeleteDialogComponent {

    occassion: OccassionSuchaz;

    constructor(
        private occassionService: OccassionSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.occassionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'occassionListModification',
                content: 'Deleted an occassion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-occassion-suchaz-delete-popup',
    template: ''
})
export class OccassionSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private occassionPopupService: OccassionSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.occassionPopupService
                .open(OccassionSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
