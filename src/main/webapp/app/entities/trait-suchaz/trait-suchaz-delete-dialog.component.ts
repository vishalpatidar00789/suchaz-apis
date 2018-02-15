import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TraitSuchaz } from './trait-suchaz.model';
import { TraitSuchazPopupService } from './trait-suchaz-popup.service';
import { TraitSuchazService } from './trait-suchaz.service';

@Component({
    selector: 'jhi-trait-suchaz-delete-dialog',
    templateUrl: './trait-suchaz-delete-dialog.component.html'
})
export class TraitSuchazDeleteDialogComponent {

    trait: TraitSuchaz;

    constructor(
        private traitService: TraitSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.traitService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traitListModification',
                content: 'Deleted an trait'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trait-suchaz-delete-popup',
    template: ''
})
export class TraitSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traitPopupService: TraitSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traitPopupService
                .open(TraitSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
