/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz-delete-dialog.component';
import { RelationshipSuchazService } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.service';

describe('Component Tests', () => {

    describe('RelationshipSuchaz Management Delete Component', () => {
        let comp: RelationshipSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<RelationshipSuchazDeleteDialogComponent>;
        let service: RelationshipSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipSuchazDeleteDialogComponent],
                providers: [
                    RelationshipSuchazService
                ]
            })
            .overrideTemplate(RelationshipSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
